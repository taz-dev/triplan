package be.triplan.config.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {

    private final JwtProvider jwtProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //헤더에서 JWT 받아오기
        String token = jwtProvider.resolveToken((HttpServletRequest) request);

        //검증
        log.info("[Verifying token]");
        log.info(((HttpServletRequest) request).getRequestURL().toString());

        //유효한 토큰인지 확인
        if (token != null && jwtProvider.validateToken(token)) {
            //토큰이 유효하면 토큰으로부터 유저 정보 반환
            Authentication authentication = jwtProvider.getAuthentication(token);
            //SecurityContext에 Authentication 객체 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }
}
