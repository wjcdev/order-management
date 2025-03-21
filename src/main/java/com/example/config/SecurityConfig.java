package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
public class SecurityConfig {
    private static final Logger log = LoggerFactory.getLogger(SecurityConfig.class);

    @PostConstruct
    public void init() {
        log.info("✅ SecurityConfig Loaded: Custom Security settings are being applied.");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        log.info("🚀 Security FilterChain is being applied!");

        http
                .csrf(csrf -> csrf.disable())  // ✅ CSRF 완전 비활성화
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/",  // ✅ 메인 페이지 허용
                                "/test",  // ✅ 테스트 페이지 허용
                                "/api/users/signup",
                                "/api/users/login",
                                "/swagger-ui/**",
                                "/swagger-resources/**",
                                "/v3/api-docs/**",
                                "/webjars/**"
                        ).permitAll()  // ✅ 특정 URL은 인증 없이 허용
                        .anyRequest().authenticated()  // ✅ 그 외 요청은 인증 필요
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(org.springframework.security.config.http.SessionCreationPolicy.STATELESS)
                )
                .formLogin(form -> form.disable())  // ✅ 기본 로그인 폼 비활성화
                .httpBasic(basic -> basic.disable())  // ✅ 기본 인증 방식 비활성화
                .logout(logout -> logout.disable());  // ✅ 기본 로그아웃 기능 비활성화

        log.info("🔥 SecurityConfig is fully loaded!");

        return http.build();
    }
}
