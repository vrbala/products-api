package com.bala.products.controller.v1;


import com.bala.products.config.ApplicationConfig;
import com.bala.products.service.KafkaProducer;
import com.bala.products.service.EventPersistenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private KafkaProducer kafkaProducer;

    @Autowired
    private ApplicationConfig config;

    @Autowired
    private EventPersistenceService eventPersistenceService;

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

        logger.info("Async running mode? - " + config.create_async);
        logger.info("Received product " + product.toString());

        if (config.create_async) {
            kafkaProducer.sendMessage(product);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } else {
            Product existingProduct = productService.findByProductId(product.getId());
            if (null == existingProduct) {
                eventPersistenceService.createProduct(product);
                return new ResponseEntity<>("Success", HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("Resource exists already", HttpStatus.CONFLICT);
            }
        }
    }

    @RequestMapping(value = "/product", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<String> createOrUpdate(@RequestBody @Valid Product product) {

        logger.info("Async running mode? - " + config.create_async);
        logger.info("Received product " + product.toString());

        if (config.create_async) {
            kafkaProducer.sendMessage(product);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } else {
            Product existingProduct = productService.findByProductId(product.getId());
            if (null == existingProduct) {
                eventPersistenceService.createProduct(product);
                return new ResponseEntity<>("Success", HttpStatus.CREATED);
            } else {
                eventPersistenceService.updateProduct(product);
                return new ResponseEntity<>("Success", HttpStatus.OK);
            }
        }
    }
}
