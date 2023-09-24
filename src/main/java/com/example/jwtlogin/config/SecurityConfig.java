package com.example.jwtlogin.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/api/hello", "/api/hello");
    }
    @Bean
    protected SecurityFilterChain webSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .requestMatchers("/api/hello").permitAll()
                .anyRequest().authenticated()

            .and()
                .formLogin() //여기 왜 빨간줄 뜨는지 모르겠음ㅠ
                .loginPage("/api/hello")
                .loginProcessingUrl("/authenticate")
                .permitAll();
//                .successHandler("api/success")
//                .failureHandler("/api/fail");

        return http.build();
    }

}