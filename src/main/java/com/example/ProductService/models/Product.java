package com.example.ProductService.models;

import com.example.ProductService.dtos.RatingDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


import java.io.Serializable;
import java.util.UUID;

@Data
@Entity(name="products")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    String name;
    String category;
    String description;
    float price;
   // RatingDto rate;

}
