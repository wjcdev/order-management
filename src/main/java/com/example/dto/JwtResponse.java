package com.example.dto;

public class JwtResponse {
    private String token;

    // 생성자
    public JwtResponse(String token) {
        this.token = token;
    }

    // Getter
    public String getToken() {
        return token;
    }

    // Setter (필요시)
    public void setToken(String token) {
        this.token = token;
    }
}
