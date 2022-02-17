package be.triplan.service;

import be.triplan.config.security.JwtProvider;
import be.triplan.dto.jwt.TokenDto;
import be.triplan.dto.jwt.TokenRequestDto;
import be.triplan.dto.oauth.KakaoRequestDto;
import be.triplan.entity.Member;
import be.triplan.exception.KakaoLoginFailedException;
import be.triplan.exception.RefreshTokenException;
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

    //카카오 회원가입 및 로그인
    @Transactional
    public TokenDto signUpAndLoginByKakao(KakaoRequestDto requestDto) {
        //DB에 회원 정보 존재하는지 확인(email, provider)
        //회원 정보가 없을 경우 -> 회원가입
        if (memberRepository.findByEmailAndProvider(requestDto.getEmail(), requestDto.getProvider()).isEmpty()) {
            memberRepository.save(requestDto.toEntity());
        }

        //회원 정보가 있을 경우 -> 로그인
        Member member = memberRepository.findByEmailAndProvider(requestDto.getEmail(), "kakao")
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
