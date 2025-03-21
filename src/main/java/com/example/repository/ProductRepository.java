package com.example.repository;

import com.example.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // 빈으로 등록
public interface ProductRepository extends JpaRepository<Product, Long> {
    // 필요한 쿼리 메서드 추가 가능
}