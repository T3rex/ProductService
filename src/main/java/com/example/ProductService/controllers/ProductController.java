package com.example.ProductService.controllers;

import com.example.ProductService.dtos.CreateProductRequestDto;
import com.example.ProductService.dtos.FakeStoreProductsDto;
import com.example.ProductService.exceptions.ProductNotFoundException;
import com.example.ProductService.models.Product;
import com.example.ProductService.services.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/products")
@RestController
public class ProductController {

    @Autowired
    @Qualifier("fakeStore")
    private ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") long id) throws ProductNotFoundException {
    //        if(id<1 || id>20){
    //            return new ResponseEntity<>(HttpStatusCode.valueOf(400));
    //        }
        Product product = productService.getProductById(id);
        return new ResponseEntity<>(product, HttpStatusCode.valueOf(200));
    }

    @GetMapping("/limit={limit}")
    public ResponseEntity< List<Product>> getProductsList(@PathVariable("limit") int limit) throws JsonProcessingException, JSONException {
        List<Product> productList = productService.getProductsList(limit);
        System.out.println(productList);
        return new ResponseEntity<>(productList, HttpStatusCode.valueOf(200));
    }

    @GetMapping("")
    public ResponseEntity<Page<Product>> getAllProducts(
            @RequestParam(value = "pageSize",defaultValue = "20") int pageSize,
            @RequestParam(value = "pageNum",defaultValue = "0") int pageNum
    ) throws JsonProcessingException, JSONException {
        Page<Product> productList = productService.getAllProducts(pageSize,pageNum);
        return new ResponseEntity<>(productList, HttpStatusCode.valueOf(200));
    }

    @PostMapping("")
    public Product createProduct(@RequestBody CreateProductRequestDto requestDto) throws JSONException{
        if(requestDto.getName() == null || requestDto.getCategory() == null || requestDto.getDescription() == null || requestDto.getPrice() == 0){
            System.out.println("Please send proper data");
            return null;
        }
        return productService.createProduct(requestDto.getName(), requestDto.getCategory(), requestDto.getDescription(), requestDto.getPrice());

    }

    @GetMapping("/name={name}")
    public Product getProductByName(@PathVariable("name") String name){
        return productService.getProductByName(name);
    }

    @GetMapping("/id={id}")
    public Product getProductById(@PathVariable("id") UUID id) throws ProductNotFoundException {
        return productService.getProductById(id);
    }

}
