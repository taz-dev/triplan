package be.triplan.dto.member;

import be.triplan.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequestDto {

    private String email;
    private String nickname;
    private String aboutMe;

    public Member toEntity() {
        return Member.builder()
                .email(email)
                .nickname(nickname)
                .aboutMe(aboutMe)
                .build();
    }
}
