package com.example.ordermanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "com.example") // 확실하게 전체 스캔되도록 지정
public class OrderManagementApplication {
	public static void main(String[] args) {
		SpringApplication.run(OrderManagementApplication.class, args);
	}
}

