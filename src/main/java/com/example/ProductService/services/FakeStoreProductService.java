package com.example.ProductService.services;

import com.example.ProductService.dtos.FakeStoreProductsDto;
import com.example.ProductService.exceptions.ProductNotFoundException;
import com.example.ProductService.models.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service("fakeStore")
public class FakeStoreProductService implements ProductService{

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Override
    public Product getProductById(long id) throws ProductNotFoundException {

        Product product = (Product)this.redisTemplate.opsForHash().get("PRODUCTS", "product_"+id);
        if(product!=null){
            return product;
        }
        String url = "https://fakestoreapi.com/products/"+id;
        FakeStoreProductsDto productDto  = this.restTemplate.getForObject(url, FakeStoreProductsDto.class);
        if(productDto == null){
            throw new ProductNotFoundException("Product with id "+id+" not found");
        }

        Product newproduct = convertToProduct(productDto);
        this.redisTemplate.opsForHash().put("PRODUCTS","product_"+id,newproduct);
        return newproduct;
    }

    private Product convertToProduct(FakeStoreProductsDto productDto){
        Product product = new Product();
        product.setName(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setCategory(productDto.getCategory());
        product.setPrice(productDto.getPrice());
        //product.setRate(productDto.getRating());
        return product;
    }

    @Override
    public List<Product> getProductsList(int limit) throws JsonProcessingException {
        String url = "https://fakestoreapi.com/products?limit="+limit;
        RestTemplate restTemplate = new RestTemplate();
        FakeStoreProductsDto[] fakeProdListJSON = restTemplate.getForObject(url, FakeStoreProductsDto[].class);
        List<Product> list = new ArrayList<Product>();
        
        for(FakeStoreProductsDto fakeprod:fakeProdListJSON){
            list.add(convertToProduct(fakeprod));
        }
        return list;
//        ObjectMapper objectMapper = new ObjectMapper();
//        List<FakeStoreProductsDto> fakeProdList = objectMapper.readValue(fakeProdListJSON, new TypeReference<>(){});
//        return fakeProdList.stream()
//                .map(this::convertToProduct)
//                .collect(Collectors.toList());
    }

    @Override
    public Page<Product> getAllProducts(int pageSize, int pageNum) throws JsonProcessingException {
        return null;
    }

    public List<Product> getAllProducts() throws JsonProcessingException {
        String url = "https://fakestoreapi.com/products";
        RestTemplate restTemplate = new RestTemplate();
        String fakeProdListJSON = restTemplate.getForObject(url,String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        List<FakeStoreProductsDto> fakeProdList = objectMapper.readValue(fakeProdListJSON, new TypeReference<>(){});
        return fakeProdList.stream()
                .map(this::convertToProduct)
                .collect(Collectors.toList());
    }

    @Override
    public Product createProduct(String name, String category, String description, float price) {
        return null;
    }

    @Override
    public Product getProductByName(String name) {
        return null;
    }

    @Override
    public Product getProductById(UUID id) throws ProductNotFoundException {
        return null;
    }
}
