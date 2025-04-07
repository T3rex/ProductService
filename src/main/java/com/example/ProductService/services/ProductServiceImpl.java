package com.example.ProductService.services;

import com.example.ProductService.exceptions.ProductNotFoundException;
import com.example.ProductService.models.Product;
import com.example.ProductService.repositories.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("dbStore")
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepository productRepository;

    @Override
    public Product getProductById(long id) throws ProductNotFoundException {
        return null;
    }

    @Override
    public List<Product> getProductsList(int limit) throws JsonProcessingException {
        return List.of();
    }

    @Override
    public Page<Product> getAllProducts(int pageSize, int pageNum) {
        return productRepository.findAll(PageRequest.of(pageNum,pageSize, Sort.by("name").descending()));
    }

    @Override
    public Product createProduct(String name, String category, String description, float price) throws JSONException {
        Product p = productRepository.findByName(name);
        if(p != null){
            System.out.println("Product already exists");
            return null;
        }
        Product product = new Product();
        product.setName(name);
        product.setCategory(category);
        product.setDescription(description);
        product.setPrice(price);
        product = productRepository.save(product);
        return product;
    }

    @Override
    public Product getProductByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public Product getProductById(UUID id) throws ProductNotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isPresent()){
            return optionalProduct.get();
        }
        else{
            throw new ProductNotFoundException("Product not found");
        }
    }

}
