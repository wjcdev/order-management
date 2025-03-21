package com.example.config;

import com.example.security.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtFilter jwtFilter) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // ✅ CSRF 비활성화
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(                                "/",  // ✅ 메인 페이지 허용
                                "/test",  // ✅ 테스트 페이지 허용
                                "/api/users/signup",
                                "/api/users/login",
                                "/swagger-ui/**",
                                "/swagger-resources/**",
                                "/v3/api-docs/**",
                                "/webjars/**").permitAll() // ✅ 인증 없이 접근 가능
                        .requestMatchers("/api/products/**").hasRole("USER")
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // ✅ 세션 사용 안 함
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class) // ✅ JWT 필터 적용
                .formLogin(form -> form.disable())
                .httpBasic(httpBasic -> httpBasic.disable());

        return http.build();
    }
}

