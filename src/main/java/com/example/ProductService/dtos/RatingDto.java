package com.example.ProductService.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//@Getter
//@Setter
@Data // short for getter and setter
public class RatingDto {
    float rate;
    int count;
}
