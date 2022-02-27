package be.triplan.dto.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberUpdateRequestDto {
    private String nickname;
    private String aboutMe;
    private String memberImage;
}
