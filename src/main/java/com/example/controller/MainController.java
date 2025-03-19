package com.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("메인 페이지입니다.");
    }

    @GetMapping("/test")
    public ResponseEntity<String> testEndpoint() {
        return ResponseEntity.ok("Test endpoint is working!");
    }
}
