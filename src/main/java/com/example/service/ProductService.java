package com.example.service;

import com.example.dto.ProductRequestDto;
import com.example.dto.ProductResponseDto;
import com.example.entity.Product;
import com.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //  상품 추가
    public ProductResponseDto addProduct(ProductRequestDto requestDto) {
        Product product = new Product(requestDto.getName(), requestDto.getDescription(), requestDto.getPrice(), requestDto.getCategory());
        product = productRepository.save(product);
        return new ProductResponseDto(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getCategory());
    }

    //  상품 목록 조회
    public List<ProductResponseDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(product -> new ProductResponseDto(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getCategory()))
                .toList();
    }

    //  특정 상품 조회
    public ProductResponseDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("상품을 찾을 수 없습니다."));
        return new ProductResponseDto(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getCategory());
    }

    //  상품 정보 수정
    public ProductResponseDto updateProduct(Long id, ProductRequestDto requestDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("상품을 찾을 수 없습니다."));

        product.setName(requestDto.getName());
        product.setDescription(requestDto.getDescription());
        product.setPrice(requestDto.getPrice());
        product.setCategory(requestDto.getCategory());
        productRepository.save(product);

        return new ProductResponseDto(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getCategory());
    }

    //  상품 삭제
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("상품을 찾을 수 없습니다."));
        productRepository.delete(product);
    }
}
