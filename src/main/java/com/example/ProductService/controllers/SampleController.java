package com.example.ProductService.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @GetMapping("/hello")
    public String helloWorld(){
        return "Hello World";
    }

    @GetMapping("/hello/{name}")
    public String getName(@PathVariable("name") String name){
        return "Name: " + name;

    }

    @GetMapping("/show/{showid}/seat/{seatid}")
    public String getShowSeat(@PathVariable("showid") String showid, @PathVariable("seatid") int seatid){
        return "Show ID: " + showid + " Seat ID: " + seatid;
    }

    public static class ProductController {
    }
}
