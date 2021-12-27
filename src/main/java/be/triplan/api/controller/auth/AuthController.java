package be.triplan.api.controller.auth;

import be.triplan.oauth.service.OAuthKakaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final OAuthKakaoService oAuthKakaoService;

    /**
     * KAKAO 소셜 로그인 기능
     * @param access_token
     * @return
     */
    @PostMapping("/kakao")
    public HashMap<String, Object> kakaoLogin(@RequestParam("access_token") String access_token) {
        HashMap<String, Object> userInfo = oAuthKakaoService.getKakaoUser(access_token);
        return userInfo;
    }

    /**
     * GOOGLE 소셜 로그인 기능
     */

    /**
     * AppToken(Triplan AccessToken) 갱신
     */
}
