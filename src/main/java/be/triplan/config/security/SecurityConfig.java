package be.triplan.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
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

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                    .addFilter(corsFilter) //시큐리티 필터에 등록(인증이 필요할 때), @CrossOrigin(인증이 필요없을 때)
                    .httpBasic().disable() //Rest API이므로 기본설정 미사용
                    .csrf().disable() //Rest API이므로 csrf 보안 미사용
                    .formLogin().disable()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS) //JWT로 인증하므로 세션 미사용
                .and()
                    .authorizeRequests()
                    .antMatchers("/social/**",
                            "/members/**",
                            "/joins/**",
                            "/plans/**",
                            "/schedules/**",
                            "/questions/**",
                            "/checklists/**",
                            "/maps/**").permitAll() //토큰 없어도 호출할 수 있도록 설정
                    .anyRequest().hasRole("USER")
                .and()
                    .addFilterBefore(new JwtAuthenticationFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class);
    }
}
