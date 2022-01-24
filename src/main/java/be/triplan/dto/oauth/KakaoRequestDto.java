package be.triplan.dto.oauth;

import be.triplan.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KakaoRequestDto {
    private String email;
    private String nickname;
    private String provider;
    private String nameTag;

    public Member toEntity() {
        return Member.builder()
                .email(email)
                .nickname(nickname)
                .provider(provider)
                .nameTag(nameTag)
                .build();
    }
}
