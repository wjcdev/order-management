package com.example.dto;

public class UserRequestDto {
    private String username;
    private String password;

    public UserRequestDto() {}  // ✅ 기본 생성자

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
