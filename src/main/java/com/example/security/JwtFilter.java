package com.example.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Key;

@Slf4j
@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            log.debug("🚨 Authorization 헤더 없음 또는 잘못된 형식: " + authorizationHeader);
            filterChain.doFilter(request, response);
            return;
        }

        String token = authorizationHeader.substring(7);
        String username = jwtUtil.validateToken(token);
        log.debug("🔍 토큰 검증 결과: username = " + username);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            log.debug("✅ JWT 검증 성공, 사용자 = " + username);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            log.debug("🔍 불러온 UserDetails: " + userDetails.getUsername() + ", 권한: " + userDetails.getAuthorities());

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.debug("✅ SecurityContextHolder에 인증 정보 저장 완료");
        } else {
            log.debug("🚨 JWT 검증 실패 또는 이미 인증됨");
        }

        filterChain.doFilter(request, response);
    }
}
