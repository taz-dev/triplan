package be.triplan.dto.member;

import be.triplan.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberLoginRequestDto {
    private String email;

    public Member toMember() {
        return Member.builder()
                .email(email)
                .build();
    }
}
