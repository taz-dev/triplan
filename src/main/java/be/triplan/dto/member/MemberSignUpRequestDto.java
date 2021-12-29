package be.triplan.dto.member;

import be.triplan.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collections;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberSignUpRequestDto {
    private String email;
    private String nickname;
    private String provider;

    public Member toEntity() {
        return Member.builder()
                .email(email)
                .nickname(nickname)
                .provider(provider)
                .roles(Collections.singletonList("ROLE_USER"))
                .build();
    }
}
