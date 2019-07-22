package com.bala.products.controller.v1;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Product {

    @RequestMapping("/api/v1/products")
    public String hello() {
        return "Hello world";
    }

}
