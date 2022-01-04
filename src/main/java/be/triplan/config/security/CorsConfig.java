package be.triplan.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowCredentials(true); //서버가 응답을 할 때 json을 자바스크립트에서 처리할 수 있게 할지를 설정하는 것
        //configuration.setAllowedOriginPatterns("*");
        configuration.addAllowedOrigin("*"); //모든 ip에 응답을 허용
        configuration.addAllowedHeader("*"); //모든 header에 응답을 허용
        configuration.addAllowedMethod("*"); //모든 post, get, put, delete, patch 요청을 허용
        configuration.addExposedHeader("Authorization");
        configuration.addExposedHeader("refreshToken");
        source.registerCorsConfiguration("/**", configuration);

        return new CorsFilter(source);
    }
}