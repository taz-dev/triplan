package be.triplan.dto.jwt;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenDto {
    private String grantType;
    private String accessToken;
    private String refreshToken;
    private Long accessTokenExpireDate;
    private String nickname;
    private String email;
    private String nameTag;
    private String aboutMe;
}
