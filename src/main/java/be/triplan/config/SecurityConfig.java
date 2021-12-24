package be.triplan.config;

import be.triplan.config.auth.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CorsFilter corsFilter;
    private final CustomOAuth2UserService customOAuth2UserService;
    //private final OAuthService oAuthService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //세션을 사용하지 않음
                .and()
                    .addFilter(corsFilter) //시큐리티 필터에 등록(인증이 필요할 때), @CrossOrigin(인증이 필요없을 때)
                    .formLogin().disable()
                    .httpBasic().disable()
                    .authorizeRequests()
                    .antMatchers("/members/**").permitAll()
                .and()
                    .oauth2Login() //OAuth2 로그인 설정 시작점
                    .userInfoEndpoint() //OAuth2 로그인 성공 이후 사용자 정보를 가져올 때 설정 담당
                    .userService(customOAuth2UserService); //OAuth2 로그인 성공 시, 후작업을 진행항 UserService 인터페이스 구현
    }

}
