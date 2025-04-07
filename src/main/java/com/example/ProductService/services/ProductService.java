package com.example.ProductService.services;
import com.example.ProductService.dtos.FakeStoreProductsDto;
import com.example.ProductService.exceptions.ProductNotFoundException;
import com.example.ProductService.models.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    Product getProductById(long id) throws ProductNotFoundException;
    List<Product> getProductsList(int limit) throws JsonProcessingException;
    Page<Product> getAllProducts(int pageSize, int pageNum) throws  JsonProcessingException;
    Product createProduct(String name, String category, String description, float price) throws JSONException;
    Product getProductByName(String name);
    Product getProductById(UUID id) throws ProductNotFoundException;
}
