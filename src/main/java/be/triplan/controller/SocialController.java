package be.triplan.controller;

import be.triplan.config.security.JwtProvider;
import be.triplan.dto.jwt.TokenDto;
import be.triplan.dto.member.MemberSignUpRequestDto;
import be.triplan.dto.member.MemberSocialLoginRequestDto;
import be.triplan.dto.member.MemberSocialSignUpRequestDto;
import be.triplan.dto.oauth.KakaoProfile;
import be.triplan.dto.response.CommonResult;
import be.triplan.dto.response.SingleResult;
import be.triplan.entity.Member;
import be.triplan.exception.CSocialAgreementException;
import be.triplan.exception.CUserNotFoundException;
import be.triplan.repository.MemberRepository;
import be.triplan.service.oauth.KakaoService;
import be.triplan.service.response.ResponseService;
import be.triplan.service.security.SignService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SocialController {

    private final JwtProvider jwtProvider;
    private final MemberRepository memberRepository;
    private final KakaoService kakaoService;
    private final SignService signService;
    private final ResponseService responseService;

    /**
     * Kakao 소셜 회원가입
     */
    @PostMapping("/social/signup/kakao")
    public CommonResult signUpByKakao(@RequestBody MemberSocialSignUpRequestDto socialSignUpRequestDto) {
        //카카오로부터 사용자 정보 요청
        KakaoProfile kakaoProfile = kakaoService.getKakaoProfile(socialSignUpRequestDto.getAccessToken());
        if (kakaoProfile == null) throw new CUserNotFoundException();
        if (kakaoProfile.getKakao_account().getEmail() == null) {
            kakaoService.kakaoUnlink(socialSignUpRequestDto.getAccessToken());
            throw new CSocialAgreementException();
        }

        Long memberId = signService.socialSignup(MemberSignUpRequestDto.builder()
                .email(kakaoProfile.getKakao_account().getEmail())
                .nickname(kakaoProfile.getProperties().getNickname())
                .provider("kakao")
                .build());

        return responseService.getSingleResult(memberId);
    }
    
    /**
     * Kakao 소셜 로그인
     */
    @PostMapping("/social/login/kakao")
    public SingleResult<TokenDto> loginByKakao(@RequestBody MemberSocialLoginRequestDto socialLoginRequestDto) {
        KakaoProfile kakaoProfile = kakaoService.getKakaoProfile(socialLoginRequestDto.getAccessToken());
        if (kakaoProfile == null) throw new CUserNotFoundException();

        Member member = memberRepository.findByEmailAndProvider(kakaoProfile.getKakao_account().getEmail(), "kakao")
                .orElseThrow(CUserNotFoundException::new);

        return responseService.getSingleResult(jwtProvider.createToken(member.getId(), member.getRoles()));
    }

    /**
     * Google 소셜 로그인
     */

    /**
     * AppToken(Triplan AccessToken) 갱신
     */
}
