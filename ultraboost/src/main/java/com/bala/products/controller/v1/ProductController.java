package com.bala.products.controller.v1;


import com.bala.products.dto.Product;
import com.bala.products.exceptions.ResourceNotFound;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.bala.products.dto.Product;
import com.bala.products.service.ProductService;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1", produces = "application/json")
public class ProductController {

    @Autowired
    private ProductService productService;

    private Gson gson = new Gson();

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    @ResponseBody
    public List<Product> products() {
        return productService.firstPage();
    }

    @RequestMapping("/product")
    @ResponseBody
    public String product(@PathVariable String productId) {

        Product product = productService.findByProductId(productId);
        if (product != null) {
            return gson.toJson(product);
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "resource not found");
    }

}
