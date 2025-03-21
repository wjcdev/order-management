package com.example.controller;

import com.example.dto.UserRequestDto;
import com.example.dto.UserResponseDto;
import com.example.entity.User;
import com.example.security.JwtUtil;
import com.example.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.dto.JwtResponse;


@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    // 생성자 주입으로 두 객체를 초기화
    public UserController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    // 회원가입 API
    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signup(@RequestBody UserRequestDto request) {
        UserResponseDto response = userService.createUser(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody UserRequestDto request) {
        // 데이터베이스에서 사용자 정보 조회
        User user = userService.findByUsername(request.getUsername());

        // 사용자가 존재하는지 확인
        if (user == null) {
            System.out.println("❌ 사용자 없음");
            return ResponseEntity.status(401).body(new JwtResponse("로그인 실패: 존재하지 않는 사용자"));
        }

        // 비밀번호 검증 (실제 서비스에서는 BCryptPasswordEncoder 사용)
        if (!user.getPassword().equals(request.getPassword())) {
            System.out.println("❌ 비밀번호 불일치");
            return ResponseEntity.status(401).body(new JwtResponse("로그인 실패: 비밀번호 불일치"));
        }

        // JWT 토큰 생성
        System.out.println("✅ 로그인 성공");
        String token = jwtUtil.generateToken(user.getUsername(), String.valueOf(user.getRole()));

        return ResponseEntity.ok(new JwtResponse(token)); // JWT 토큰 반환
    }
}
