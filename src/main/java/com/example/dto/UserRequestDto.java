package com.example.dto;

public class UserRequestDto {
    private String username;
    private String password;

    public UserRequestDto() {}

    public UserRequestDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
}
