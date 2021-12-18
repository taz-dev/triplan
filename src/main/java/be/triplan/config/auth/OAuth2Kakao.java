package be.triplan.config.auth;

import be.triplan.config.auth.dto.AuthorizationKakao;
import be.triplan.exception.ProcyanException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class OAuth2Kakao {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    private final String kakaoOAuth2ClientId = "f830ac604b909cbe2f28044801646ad4";
    private final String frontendRedirectUri = "다시 수정해야 됨";

    /**
     * AccessToken 발급받는 역할
     * @return
     */
    public AuthorizationKakao callTokenApi(String code) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String grantType = "authorization_code"; //고정
        
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", grantType);
        params.add("client_id", kakaoOAuth2ClientId);
        params.add("redirect_uri", frontendRedirectUri);
        params.add("code", code);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        String tokenRequestUrl = "https://kauth.kakao.com/oauth/token";

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(tokenRequestUrl, request, String.class);
            AuthorizationKakao authorizationKakao = objectMapper.readValue(response.getBody(), AuthorizationKakao.class);

            return authorizationKakao;

        } catch (RestClientException | JsonProcessingException ex) {
            ex.printStackTrace();
            throw new ProcyanException(E00001);
        }
    }

    /**
     * AccessToken 이용해서 유저정보 받기
     * @return
     */
    public String callGetUserByAccessToken(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        String url = "https://kapi.kakao.com/v2/user/me";

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

            return response.getBody();

        }catch (RestClientException ex) {
            ex.printStackTrace();
            throw new ProcyanException(E00002);
        }
    }
}
