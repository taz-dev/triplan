package be.triplan.config.auth.dto;

import be.triplan.entity.Member;
import be.triplan.entity.Role;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {

    private Map<String, Object> attributes; // OAuth2 반환하는 유저 정보 Map
    private String nameAttributeKey;
    private String nickname;
    private String email;
    private String imageUrl;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String nickname, String email, String imageUrl) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.nickname = nickname;
        this.email = email;
        this.imageUrl = imageUrl;
    }

    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes){
        //naver
        if ("naver".equals(registrationId)) {
            return ofNaver("id", attributes);
        }

        //google
        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        // JSON 형태 이기 떄문에 Map을 통해서 데이터를 가져옴
        Map<String, Object> response = (Map<String, Object>)attributes.get("response");

        return OAuthAttributes.builder()
                .nickname((String) response.get("nickname"))
                .email((String) response.get("email"))
                .imageUrl((String) response.get("profile_image"))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .nickname((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .imageUrl((String) attributes.get("picture"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public Member toEntity(){
        return Member.builder()
                .nickname(nickname)
                .email(email)
                .imageUrl(imageUrl)
                .role(Role.GUEST) // 기본 권한 GUEST
                .build();
    }

}
