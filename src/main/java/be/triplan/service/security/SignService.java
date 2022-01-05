package be.triplan.service.security;

import be.triplan.config.security.JwtProvider;
import be.triplan.dto.jwt.TokenDto;
import be.triplan.dto.jwt.TokenRequestDto;
import be.triplan.dto.member.MemberLoginRequestDto;
import be.triplan.dto.member.MemberSignUpRequestDto;
import be.triplan.entity.Member;
import be.triplan.entity.security.RefreshToken;
import be.triplan.exception.TEmailLoginFailedException;
import be.triplan.exception.TRefreshTokenException;
import be.triplan.exception.TUserExistException;
import be.triplan.exception.TUserNotFoundException;
import be.triplan.repository.MemberRepository;
import be.triplan.repository.security.RefreshTokenRepository;
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
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public TokenDto socialLogin(MemberLoginRequestDto memberLoginRequestDto) {
        //회원 정보 존재하는지 확인
        Member member = memberRepository.findByEmail(memberLoginRequestDto.getEmail())
                .orElseThrow(TEmailLoginFailedException::new);

        //access token, refresh token 발급
        TokenDto tokenDto = jwtProvider.createToken(member.getId(), member.getRoles());

        //refresh token 저장
        RefreshToken refreshToken = RefreshToken.builder()
                .key(member.getId())
                .token(tokenDto.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);

        return tokenDto;
    }

    @Transactional
    public Long socialSignup(MemberSignUpRequestDto memberSignRequestDto) {
        if (memberRepository
                .findByEmailAndProvider(memberSignRequestDto.getEmail(), memberSignRequestDto.getProvider())
                .isPresent()
        ) throw new TUserExistException(); //이미 가입된 유저 Exception

        return memberRepository.save(memberSignRequestDto.toEntity()).getId();
    }

    @Transactional
    public TokenDto reissue(TokenRequestDto tokenRequestDto) {
        //만료된 refresh token 에어
        if (!jwtProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            throw new TRefreshTokenException(); //만들어야 됨
        }

        //access token에서 username(pk) 가져오기
        String accessToken = tokenRequestDto.getAccessToken();
        Authentication authentication = jwtProvider.getAuthentication(accessToken);

        //member pk로 유저 검색(repository에 저장된 refresh token이 없음)
        Member member = memberRepository.findById(Long.parseLong(authentication.getName()))
                .orElseThrow(TUserNotFoundException::new);
        RefreshToken refreshToken = refreshTokenRepository.findByKey(member.getId())
                .orElseThrow(TRefreshTokenException::new);

        //refresh token 불일치 에러
        if (!refreshToken.getToken().equals(tokenRequestDto.getRefreshToken()))
            throw new TRefreshTokenException();

        //access token, refresh token 토큰 재발급, refresh token 저장
        TokenDto newToken = jwtProvider.createToken(member.getId(), member.getRoles());
        RefreshToken updateRefreshToken = refreshToken.updateToken(newToken.getRefreshToken());
        refreshTokenRepository.save(updateRefreshToken);

        return newToken;
    }

}
