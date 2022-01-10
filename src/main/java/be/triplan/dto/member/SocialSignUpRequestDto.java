package be.triplan.dto.member;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SocialSignUpRequestDto {
    private String accessToken;
}
