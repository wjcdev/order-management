package com.example.service;

import com.example.dto.UserRequestDto;
import com.example.dto.UserResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public void createUser(String username) {
        logger.info("사용자 생성 요청: {}", username);
        // 비즈니스 로직
    }

    public UserResponseDto createUser(UserRequestDto request) {
        // 실제 DB 저장 로직 추가 (예제에서는 단순 응답)
        return new UserResponseDto(1L, request.getUsername());
    }
}
