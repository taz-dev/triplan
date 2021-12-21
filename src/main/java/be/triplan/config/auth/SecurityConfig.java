package be.triplan.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;
    //private final OAuthService oAuthService;

    @Autowired
    CorsFilter corsFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/members/**").permitAll()
                .and()
                    .addFilter(corsFilter)
                    .oauth2Login() //OAuth2 로그인 설정 시작점
                    .userInfoEndpoint() //OAuth2 로그인 성공 이후 사용자 정보를 가져올 때 설정 담당
                    .userService(customOAuth2UserService); //OAuth2 로그인 성공 시, 후작업을 진행항 UserService 인터페이스 구현
    }

}
