package be.triplan.dto.oauth;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KakaoAccessTokenDto {
    private String accessToken;
}
