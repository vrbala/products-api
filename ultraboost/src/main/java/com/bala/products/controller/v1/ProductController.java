package com.bala.products.controller.v1;


import com.bala.products.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bala.products.dto.Product;
import com.bala.products.service.ProductService;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1", produces = "application/json")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Product product(@PathVariable(value = "id") String productId) {

        Product product = productService.findByProductId(productId);
        if (product != null) {
            return product;
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "resource not found");
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    @ResponseBody
    public List<Product> list(@RequestParam(value = "limit", required = false) Integer limit,
                                       @RequestParam(value = "page", required = false) Integer page) {
        if(limit == null || page == null) {
            return productService.firstPage();
        } else {
            return productService.pagedResults(limit, page);
        }
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> create(@RequestBody @Valid Product product) {
        Product existingProduct = productService.findByProductId(product.getId());
        if(null == existingProduct) {
            productService.createProduct(product);
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        } else {
            productService.updateProduct(product);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }
    }

}
