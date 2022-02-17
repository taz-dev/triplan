package be.triplan.dto.jwt;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class RefreshTokenDto {
    private String refreshToken;
}
