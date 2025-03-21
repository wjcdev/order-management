package com.example.ordermanagement;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = {
		"com.example.config",
		"com.example.controller",
		"com.example.repository",
		"com.example.service",
		"com.example.entity",
		"com.example.security"
})

@EnableJpaRepositories(basePackages = "com.example.repository") // 📌 JPA Repository 스캔
@EntityScan(basePackages = "com.example.entity") // 📌 엔티티 스캔 추가
public class OrderManagementApplication {
	public static void main(String[] args) {
		SpringApplication.run(OrderManagementApplication.class, args);
	}


}