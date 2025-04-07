package com.example.ProductService.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class FakeStoreProductsDto {
    long id;
    String title;
    float price;
    String description;
    String category;
    String image;
    RatingDto rating;
}
