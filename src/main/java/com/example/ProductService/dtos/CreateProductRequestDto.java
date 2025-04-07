package com.example.ProductService.dtos;

import lombok.Data;

@Data
public class CreateProductRequestDto {
    String name;
    String category;
    String description;
    float price;
}
