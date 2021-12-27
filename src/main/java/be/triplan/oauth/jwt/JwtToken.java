/*package be.triplan.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.Map;

public class JwtToken {

    @Value("${security.oauth2.jwt.secret-key}")
    private String secretKey;

    public static String generatedToken() {

        Map<String, Object> headers = new HashMap<>();
        headers.put("typ", "JWT");
        headers.put("alg", "HS256");

        Map<String, Object> payloads = new HashMap<>();
        Long expiredTime = 1800000L;

        String jwt = Jwts.builder()
                .setHeader(headers)
                .setClaims(payloads)
                .signWith()
                .compact();

        return jwt;
    }
}*/
