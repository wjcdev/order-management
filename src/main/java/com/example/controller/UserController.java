package com.example.controller;

import com.example.dto.UserRequestDto;
import com.example.dto.UserResponseDto;
import com.example.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원가입 API
    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signup(@RequestBody UserRequestDto request) {
        UserResponseDto response = userService.createUser(request);
        return ResponseEntity.ok(response);
    }
}
