package be.triplan.service.security;

import be.triplan.config.security.JwtProvider;
import be.triplan.dto.jwt.TokenDto;
import be.triplan.dto.jwt.TokenRequestDto;
import be.triplan.dto.member.MemberLoginRequestDto;
import be.triplan.dto.member.MemberSignUpRequestDto;
import be.triplan.entity.Member;
import be.triplan.exception.KakaoLoginFailedException;
import be.triplan.exception.RefreshTokenException;
import be.triplan.exception.UserExistException;
import be.triplan.exception.UserNotFoundException;
import be.triplan.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SignService {

    private final JwtProvider jwtProvider;
    private final MemberRepository memberRepository;
    
    //카카오 회원가입
    @Transactional
    public Long signUpByKakao(MemberSignUpRequestDto memberSignRequestDto) {
        if (memberRepository
                .findByEmailAndProvider(memberSignRequestDto.getEmail(), memberSignRequestDto.getProvider())
                .isPresent()
        ) throw new UserExistException(); //이미 가입된 유저 Exception

        return memberRepository.save(memberSignRequestDto.toEntity()).getId();
    }
    
    //카카오 로그인
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

    //카카오 자동 로그인
    @Transactional
    public TokenDto autoLoginByKakao(String refreshToken) {
        //refresh token 으로 회원 정보 가져오기
        Member member = memberRepository.findByRefreshToken(refreshToken)
                .orElseThrow(KakaoLoginFailedException::new);

        //access token, refresh token 재발급
        TokenDto tokenDto = jwtProvider.createToken(member.getId(), member.getRoles());
        member.updateRefreshToken(tokenDto.getRefreshToken());

        //refresh token 업데이트
        memberRepository.save(member);

        return tokenDto;
    }

    //Access Token, Refresh Token 재발급
    @Transactional
    public TokenDto reissue(TokenRequestDto tokenRequestDto) {
        //만료된 refresh token 에러
        if (!jwtProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            throw new RefreshTokenException();
        }

        //access token 에서 username(pk) 가져오기
        String accessToken = tokenRequestDto.getAccessToken();
        Authentication authentication = jwtProvider.getAuthentication(accessToken);

        //member pk로 유저 검색 (repository 에 저장된 refresh token 이 없음)
        Member member = memberRepository.findById(Long.parseLong(authentication.getName()))
                .orElseThrow(UserNotFoundException::new);

        Member findMember = memberRepository.findByRefreshToken(member.getRefreshToken())
                .orElseThrow(RefreshTokenException::new);

        //refresh token 불일치 에러
        if (!findMember.getRefreshToken().equals(tokenRequestDto.getRefreshToken()))
            throw new RefreshTokenException();

        //access token, refresh token 토큰 재발급
        TokenDto newToken = jwtProvider.createToken(member.getId(), member.getRoles());
        member.updateRefreshToken(newToken.getRefreshToken());

        //refresh token 저장
        memberRepository.save(member);

        return newToken;
    }
}
