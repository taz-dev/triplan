package be.triplan.controller;

import be.triplan.config.auth.service.OAuthKakaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final OAuthKakaoService oAuthKakaoService;

    @GetMapping("/kakao")
    public HashMap<String, Object> kakaoLogin(@RequestParam("access_token") String access_token) {
        HashMap<String, Object> userInfo = oAuthKakaoService.getKakaoUser(access_token);
        return userInfo;
    }
}
