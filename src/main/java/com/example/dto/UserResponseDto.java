package com.example.dto;

public class UserResponseDto {
    private Long id;
    private String username;

    public UserResponseDto(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public Long getId() { return id; }
    public String getUsername() { return username; }
}
