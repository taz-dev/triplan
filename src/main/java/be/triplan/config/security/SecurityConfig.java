package be.triplan.config.security;

import be.triplan.exception.RestAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtProvider jwtProvider;
    private final CorsFilter corsFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                    .cors()
                .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS) //세션을 사용하지 않음
                .and()
                    //.addFilter(corsFilter) //시큐리티 필터에 등록(인증이 필요할 때), @CrossOrigin(인증이 필요없을 때)
                    .csrf().disable()
                    .formLogin().disable()
                    .httpBasic().disable()
                    .exceptionHandling()
                    .authenticationEntryPoint(new RestAuthenticationEntryPoint())
                .and()
                    //.addFilter(new JwtAuthenticationFilter(authenticationManager()))
                    .authorizeRequests()
                    .antMatchers("/members/**","/social/**").permitAll() //토큰 없어도 호출할 수 있도록 설정
                    .antMatchers("/oauth/kakao/**").permitAll()
                    .anyRequest().hasRole("USER")
//                .and()
//                    .oauth2Login() //OAuth2 로그인 설정 시작점
//                    .authorizationEndpoint()
//                    .baseUri("/oauth2/authorization") //다시 설정
//                .and()
//                    .redirectionEndpoint()
//                    .baseUri("/*/oauth2/code/*") //다시 설정
//                .and()
//                    .userInfoEndpoint() //OAuth2 로그인 성공 이후 사용자 정보를 가져올 때 설정 담당
//                    .userService(customOAuth2UserService); //OAuth2 로그인 성공 시, 후작업을 진행항 UserService 인터페이스 구현
                //.and()
                    //.successHandler(oAuth2AuthenticationSuccessHandler)
                    //.failureHandler(oAuth2AuthenticationFailureHandler);
                .and()
                    .addFilterBefore(new JwtAuthenticationFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class);
    }

}
