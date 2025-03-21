package com.example.dto;

import lombok.Getter;

@Getter
public class ProductResponseDto {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String category;

    public ProductResponseDto(Long id, String name, String description, Double price, String category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    // getter
}
