package be.triplan.dto.member;

import be.triplan.entity.Member;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberSocialLoginRequestDto {
    private String accessToken;
    private String email;

    public Member toMember() {
        return Member.builder()
                .email(email)
                .build();
    }
}
