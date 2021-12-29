package be.triplan.dto.member;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberSocialLoginRequestDto {
    private String accessToken;
}
