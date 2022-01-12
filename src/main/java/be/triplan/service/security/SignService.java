package be.triplan.service.security;

import be.triplan.config.security.JwtProvider;
import be.triplan.dto.jwt.TokenDto;
import be.triplan.dto.member.MemberAutoLoginResponseDto;
import be.triplan.dto.member.MemberLoginRequestDto;
import be.triplan.dto.member.MemberSignUpRequestDto;
import be.triplan.entity.Member;
import be.triplan.exception.KakaoLoginFailedException;
import be.triplan.exception.UserExistException;
import be.triplan.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SignService {

    private final JwtProvider jwtProvider;
    private final MemberRepository memberRepository;

    @Transactional
    public Long signUpByKakao(MemberSignUpRequestDto memberSignRequestDto) {
        if (memberRepository
                .findByEmailAndProvider(memberSignRequestDto.getEmail(), memberSignRequestDto.getProvider())
                .isPresent()
        ) throw new UserExistException(); //이미 가입된 유저 Exception

        return memberRepository.save(memberSignRequestDto.toEntity()).getId();
    }

    @Transactional
    public TokenDto loginByKakao(MemberLoginRequestDto memberLoginRequestDto) {
        //회원 정보 존재하는지 확인
        Member member = memberRepository.findByEmailAndProvider(memberLoginRequestDto.getEmail(), "kakao")
                .orElseThrow(KakaoLoginFailedException::new);

        //access token, refresh token 발급
        TokenDto tokenDto = jwtProvider.createToken(member.getId(), member.getRoles());
        member.updateRefreshToken(tokenDto.getRefreshToken());

        //refresh token 저장
        memberRepository.save(member);

        return tokenDto;
    }

    //자동 로그인
    @Transactional
    public MemberAutoLoginResponseDto autoLoginByKakao(String refreshToken) {
        Member member = memberRepository.findByRefreshToken(refreshToken)
                .orElseThrow(KakaoLoginFailedException::new);
        return new MemberAutoLoginResponseDto(member);
    }

/*    @Transactional
    public TokenDto reissue(TokenRequestDto tokenRequestDto) {
        //만료된 refresh token 에어
        if (!jwtProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            throw new RefreshTokenException(); //만들어야 됨
        }

        //access token에서 username(pk) 가져오기
        String accessToken = tokenRequestDto.getAccessToken();
        Authentication authentication = jwtProvider.getAuthentication(accessToken);

        //member pk로 유저 검색(repository에 저장된 refresh token이 없음)
        Member member = memberRepository.findById(Long.parseLong(authentication.getName()))
                .orElseThrow(UserNotFoundException::new);
        RefreshToken refreshToken = refreshTokenRepository.findByKey(member.getId())
                .orElseThrow(RefreshTokenException::new);

        //refresh token 불일치 에러
        if (!refreshToken.getToken().equals(tokenRequestDto.getRefreshToken()))
            throw new RefreshTokenException();

        //access token, refresh token 토큰 재발급, refresh token 저장
        TokenDto newToken = jwtProvider.createToken(member.getId(), member.getRoles());
        RefreshToken updateRefreshToken = refreshToken.updateToken(newToken.getRefreshToken());
        refreshTokenRepository.save(updateRefreshToken);

        return newToken;
    }*/
}
