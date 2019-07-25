package com.bala.products.controller.v1;


import com.bala.products.dto.*;
import com.bala.products.service.KafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bala.products.dto.Product;
import com.bala.products.service.ProductService;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping(value = "/api/v1", produces = "application/json")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Value("${ultraboost.create_product_async}")
    private boolean create_async;

    @Autowired
    private ProductService productService;

    @Autowired
    private KafkaProducer kafkaProducer;

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
    public ResponseEntity<String> createOrUpdate(@RequestBody @Valid Product product) {

        logger.info("Async running mode? - " + create_async);
        logger.info("Received product " + product.toString());

        if (create_async) {
            kafkaProducer.sendMessage(product);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } else {
            Product existingProduct = productService.findByProductId(product.getId());
            if (null == existingProduct) {
                productService.createProduct(product);
                return new ResponseEntity<>("Success", HttpStatus.CREATED);
            } else {
                productService.updateProduct(product);
                return new ResponseEntity<>("Success", HttpStatus.OK);
            }
        }
    }

    // TODO: should be admin only endpoint.
    @RequestMapping(value = "/sample", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> setupSampleProducts() {

        MetaData md = new MetaData()
                .setCanonical("//www.adidas.co.uk/nite-jogger-shoes/product.html")
                .setDescription("Shop for Nite Jogger Shoes - Black at adidas.co.uk! See all the styles " +
                        "and colours of Nite Jogger Shoes - Black at the official adidas UK online store.")
                .setKeywords("Nite Jogger Shoes")
                .setSiteName("adidas United Kingdom")
                .setPageTitle("adidas Nite Jogger Shoes - Black | adidas UK");

        PricingInformation pi = new PricingInformation()
                .setCurrentPrice(119.95)
                .setStandardPrice(199.95)
                .setStandardPriceNoVat(119.95);

        ProductDescription pd = new ProductDescription()
                .setSubtitle("Modern cushioning updates this flashy'80s standout.")
                .setTitle("Nite Jogger Shoes")
                .setText("Inspired by the 1980 Nite Jogger, these shoes shine bright with retro style and " +
                        " reflective details. The mesh and nylon ripstop upper is detailed with suede overlays. An " +
                        " updated Boost midsole adds responsive cushioning for all-day comfort.");

        List<Integer> range = IntStream.range(10, 91).boxed().collect(Collectors.toList());
        for (int i : range) {
            String pId = String.format("CG70%d", i);
            String modelNumber = String.format("BT0%d", i);

            Product p = new Product()
                    .setId(pId)
                    .setModelNumber(modelNumber)
                    .setName("Nite Jogger Lite")
                    .setProductType("inline")
                    .setMetaData(md)
                    .setPricingInformation(pi)
                    .setProductDescription(pd);

            kafkaProducer.sendMessage(p);
        }

        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

}
