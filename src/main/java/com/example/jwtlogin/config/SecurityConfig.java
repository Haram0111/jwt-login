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
        return (web) -> web.ignoring().requestMatchers("/h2-console/**", "/favicon.ico");
    }
    @Bean
    protected SecurityFilterChain webSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .requestMatchers("/api/hello").permitAll() //api/hello는 권한이 없어도 된다
                .anyRequest().authenticated(); //그 외 모든 페이지는 권한이 있어야한다

//            .and()
//                .formLogin() //여기 왜 빨간줄 뜨는지 모르겠음ㅠ
//                .loginPage("/api/hello")
//                .loginProcessingUrl("/authenticate")
//                .permitAll();
//                .successHandler("api/success")
//                .failureHandler("/api/fail");

        return http.build();
    }

}