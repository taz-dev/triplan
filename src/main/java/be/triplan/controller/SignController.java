package be.triplan.controller;

import be.triplan.dto.common.SingleResult;
import be.triplan.dto.jwt.RefreshTokenDto;
import be.triplan.dto.jwt.TokenDto;
import be.triplan.dto.jwt.TokenRequestDto;
import be.triplan.dto.oauth.KakaoRequestDto;
import be.triplan.dto.oauth.KakaoAccessTokenDto;
import be.triplan.dto.oauth.KakaoProfile;
import be.triplan.exception.SocialAgreementException;
import be.triplan.exception.UserNotFoundException;
import be.triplan.service.MemberService;
import be.triplan.service.common.ResponseService;
import be.triplan.service.oauth.KakaoService;
import be.triplan.service.SignService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SignController {

    private final KakaoService kakaoService;
    private final MemberService memberService;
    private final SignService signService;
    private final ResponseService responseService;

    /**
     * KAKAO 소셜 회원가입 및 로그인
     */
    @PostMapping("/social/login/kakao")
    public SingleResult<TokenDto> signUpAndLoginByKakao(@RequestBody KakaoAccessTokenDto accessTokenDto) {
        //카카오로부터 사용자 정보 가져오기
        KakaoProfile kakaoProfile = kakaoService.getKakaoProfile(accessTokenDto.getAccessToken());
        if (kakaoProfile == null) throw new UserNotFoundException();

        if (kakaoProfile.getKakao_account().getEmail() == null) {
            kakaoService.kakaoUnlink(accessTokenDto.getAccessToken());
            throw new SocialAgreementException();
        }

        //nameTag 생성하기
        String nameTag = memberService.createNameTag();

        //jwt 토큰 생성하기
        TokenDto tokenDto = signService.signUpAndLoginByKakao(KakaoRequestDto.builder()
                .email(kakaoProfile.getKakao_account().getEmail())
                .nickname(kakaoProfile.getProperties().getNickname())
                .provider("kakao")
                .nameTag(nameTag)
                .build());

        return responseService.getSingleResult(tokenDto);
    }

    /**
     * KAKAO 자동 로그인 ---> 구글, 네이버는 일단 나중에 생각
     * 1. DB에 refresh token 이 있을 경우 자동으로 로그인
     * 2. 다시 jwt 생성해서 DB에 refresh token UPDATE
     */
    @PostMapping("/social/autologin/kakao")
    public SingleResult<TokenDto> autoLoginByKakao(@RequestBody RefreshTokenDto refreshTokenDto) {
        TokenDto tokenDto = signService.autoLoginByKakao(refreshTokenDto.getRefreshToken());
        return responseService.getSingleResult(tokenDto);
    }

    @GetMapping("/social/logout/kakao")
    public void logout(@PathVariable String accessToken){
        kakaoService.kakaoUnlink("EEBvmaF1Oyait-UHUTDasIQDnqiUQoErmfULoAo9c00AAAF-CwdHSA");
        log.info("로그아웃성공");
    }

    /**
     * Access Token, Refresh Token 재발급
     */
    @PostMapping("/reissue")
    public SingleResult<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        return responseService.getSingleResult(signService.reissue(tokenRequestDto));
    }

    /**
     * GOOGLE 소셜 회원가입 및 로그인
     */

    /**
     * NAVER 소셜 회원가입 및 로그인
     */
}
