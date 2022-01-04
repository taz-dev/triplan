package be.triplan.config.security;

import be.triplan.dto.jwt.TokenDto;
import be.triplan.exception.CAuthenticationEntryPointException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.Base64UrlCodec;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtProvider {

    @Value("jwt.secret")
    private String secretKey;
    private String ROLES = "roles";
    private final Long accessTokenExpiry = 60 * 60 * 1000L; //1시간
    private final Long refreshTokenExpiry = 14 * 24 * 60 * 60 * 1000L; //2주
    private final UserDetailsService userDetailsService;

    @PostConstruct
    protected void init() {
        //암호화
        secretKey = Base64UrlCodec.BASE64URL.encode(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    //Jwt 생성
    public TokenDto createToken(Long memberId, List<String> roles) {
        //Claims에 member 구분을 위한 Member pk 및 authorities 목록 삽입
        Claims claims = Jwts.claims().setSubject(String.valueOf(memberId));
        claims.put(ROLES, roles);

        //생성날짜, 만료날짜를 위한 Date
        Date now = new Date();

        String accessToken = Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setClaims(claims)
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .setExpiration(new Date(now.getTime() + accessTokenExpiry))
                .compact();

        String refreshToken = Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .setExpiration(new Date(now.getTime() + refreshTokenExpiry))
                .compact();

        return TokenDto.builder()
                .grantType("Bearer")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .accessTokenExpireDate(accessTokenExpiry)
                .build();
    }

    //Jwt로 인증정보를 조회
    public Authentication getAuthentication(String token) {
        // Jwt 에서 claims 추출
        Claims claims = parseClaims(token);
        // 권한 정보가 없음
        if (claims.get(ROLES) == null) {
            throw new CAuthenticationEntryPointException();
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(claims.getSubject());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    //Jwt 토큰 복호화해서 가져오기
    private Claims parseClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    //HTTP request의 Header에서 Token Parsing -> "X-AUTH-TOKEN: jwt"
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("X-AUTH-TOKEN");
    }

    //Jwt의 유효성 및 만료일자 확인
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;

        } catch (SecurityException | MalformedJwtException e) {
            log.error("잘못된 Jwt 서명입니다.");
        } catch (ExpiredJwtException e) {
            log.error("만료된 Jwt 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.error("지원하지 않는 Jwt 토큰입니다.");
        } catch (IllegalArgumentException e) {
            log.error("잘못된 Jwt 토큰입니다.");
        }
        return false;
    }
}
