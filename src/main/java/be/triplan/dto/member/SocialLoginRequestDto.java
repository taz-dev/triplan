package be.triplan.dto.member;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SocialLoginRequestDto {
    private String accessToken;
}
