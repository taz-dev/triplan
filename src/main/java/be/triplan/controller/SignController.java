package be.triplan.controller;

import be.triplan.config.security.JwtProvider;
import be.triplan.dto.jwt.TokenDto;
import be.triplan.dto.jwt.TokenRequestDto;
import be.triplan.dto.member.MemberSignUpRequestDto;
import be.triplan.dto.member.SocialLoginRequestDto;
import be.triplan.dto.member.SocialSignUpRequestDto;
import be.triplan.dto.oauth.KakaoProfile;
import be.triplan.dto.common.CommonResult;
import be.triplan.dto.common.SingleResult;
import be.triplan.entity.Member;
import be.triplan.exception.SocialAgreementException;
import be.triplan.exception.UserNotFoundException;
import be.triplan.repository.MemberRepository;
import be.triplan.service.oauth.KakaoService;
import be.triplan.service.common.ResponseService;
import be.triplan.service.security.SignService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SignController {

    private final JwtProvider jwtProvider;
    private final MemberRepository memberRepository;
    private final KakaoService kakaoService;
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

        Long memberId = signService.signUpByKakao(MemberSignUpRequestDto.builder()
                .email(kakaoProfile.getKakao_account().getEmail())
                .nickname(kakaoProfile.getProperties().getNickname())
                .provider("kakao")
                .build());

        return responseService.getSingleResult(memberId);
    }
    
    /**
     * Kakao 소셜 로그인
     */
    @CrossOrigin
    @PostMapping("/social/login/kakao")
    public SingleResult<TokenDto> loginByKakao(@RequestBody SocialLoginRequestDto socialLoginRequestDto) {
        KakaoProfile kakaoProfile = kakaoService.getKakaoProfile(socialLoginRequestDto.getAccessToken());
        if (kakaoProfile == null) throw new UserNotFoundException();

        Member member = memberRepository.findByEmailAndProvider(kakaoProfile.getKakao_account().getEmail(), "kakao")
                .orElseThrow(UserNotFoundException::new);

        //TokenDto tokenDto = signService.loginByKakao();

        //signService.socialLogin(socialLoginRequestDto); //refresh token DB 저장
        
        return responseService.getSingleResult(jwtProvider.createToken(member.getId(), member.getRoles()));
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
    @PostMapping("/reissue")
    public SingleResult<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        return responseService.getSingleResult(signService.reissue(tokenRequestDto));
    }
}
