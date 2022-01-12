package be.triplan.controller;

import be.triplan.dto.common.CommonResult;
import be.triplan.dto.common.SingleResult;
import be.triplan.dto.jwt.RefreshTokenDto;
import be.triplan.dto.jwt.TokenDto;
import be.triplan.dto.member.MemberLoginRequestDto;
import be.triplan.dto.member.MemberSignUpRequestDto;
import be.triplan.dto.member.SocialLoginRequestDto;
import be.triplan.dto.member.SocialSignUpRequestDto;
import be.triplan.dto.oauth.KakaoProfile;
import be.triplan.exception.SocialAgreementException;
import be.triplan.exception.UserNotFoundException;
import be.triplan.service.MemberService;
import be.triplan.service.common.ResponseService;
import be.triplan.service.oauth.KakaoService;
import be.triplan.service.security.SignService;
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
     * Kakao 소셜 회원가입
     */
    @PostMapping("/social/signup/kakao")
    public CommonResult signUpByKakao(@RequestBody SocialSignUpRequestDto socialSignUpRequestDto) {
        //카카오로부터 사용자 정보 요청
        KakaoProfile kakaoProfile = kakaoService.getKakaoProfile(socialSignUpRequestDto.getAccessToken());
        if (kakaoProfile == null) throw new UserNotFoundException();

        if (kakaoProfile.getKakao_account().getEmail() == null) {
            kakaoService.kakaoUnlink(socialSignUpRequestDto.getAccessToken());
            throw new SocialAgreementException();
        }

        String nameTag = memberService.createNameTag();

        Long memberId = signService.signUpByKakao(MemberSignUpRequestDto.builder()
                .email(kakaoProfile.getKakao_account().getEmail())
                .nickname(kakaoProfile.getProperties().getNickname())
                .provider("kakao")
                .nameTag(nameTag)
                .build());

        return responseService.getSingleResult(memberId);
    }
    
    /**
     * Kakao 소셜 로그인
     */
    @PostMapping("/social/login/kakao")
    public SingleResult<TokenDto> loginByKakao(@RequestBody SocialLoginRequestDto socialLoginRequestDto) {
        KakaoProfile kakaoProfile = kakaoService.getKakaoProfile(socialLoginRequestDto.getAccessToken());
        if (kakaoProfile == null) throw new UserNotFoundException();

        TokenDto tokenDto = signService.loginByKakao(MemberLoginRequestDto.builder()
                .email(kakaoProfile.getKakao_account().getEmail())
                .build());

        return responseService.getSingleResult(tokenDto);
    }

    /**
     * Kakao 자동 로그인 ---> 구글, 네이버는 일단 나중에 생각
     * --> DB에 refresh token이 있을 경우 자동으로 로그인
     * --> 다시 JWT 생성해서 DB에 refresh token UPDATE
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
     * Google 소셜 로그인
     */

    /**
     * access token, refresh token 재발급
     */
/*    @PostMapping("/reissue")
    public SingleResult<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        return responseService.getSingleResult(signService.reissue(tokenRequestDto));
    }*/
}
