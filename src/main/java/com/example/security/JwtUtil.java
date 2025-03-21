package com.example.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Component
public class JwtUtil {
    private static final String SECRET_KEY = Base64.getEncoder().encodeToString("MySuperSecretKeyMySuperSecretKey".getBytes());
    private final Key key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(SECRET_KEY));

    public String generateToken(String username, String role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role) // ✅ role 정보 추가
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String validateToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(Base64.getDecoder().decode(SECRET_KEY))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            log.debug("🔍 JWT Claims: " + claims);
            return claims.getSubject();
        } catch (JwtException e) {
            log.debug("🚨 JWT 검증 실패: " + e.getMessage());
            return null;
        }
    }

}