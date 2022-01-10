package be.triplan.service.oauth;

import be.triplan.dto.oauth.KakaoProfile;
import be.triplan.exception.CommunicationException;
import be.triplan.exception.KakaoCommunicationFailureException;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoService {

    private final RestTemplate restTemplate;
    private final Gson gson;

    //카카오 유저 정보 가져오기
    public KakaoProfile getKakaoProfile(String kakaoAccessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "Bearer " + kakaoAccessToken);

        String requestUrl = "https://kapi.kakao.com/v2/user/me";
        //if (requestUrl == null) throw new CommunicationException();

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(null, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(requestUrl, request, String.class);
            if (response.getStatusCode() == HttpStatus.OK)
                return gson.fromJson(response.getBody(), KakaoProfile.class);

        } catch (Exception e) {
            log.error(e.toString());
            throw new KakaoCommunicationFailureException();
        }
        throw new KakaoCommunicationFailureException();
    }

    //카카오 유저와 연결 끊기
    public void kakaoUnlink(String accessToken) {
        String unlinkUrl = "https://kapi.kakao.com/v1/user/unlink";
        if (unlinkUrl == null) throw new CommunicationException();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "Bearer " + accessToken);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(unlinkUrl, request, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            log.info("unlink " + response.getBody());
            return;
        }
        throw new KakaoCommunicationFailureException();
    }
}
