package com.bala.products.service;

import com.bala.products.dto.Product;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class KafkaConsumer {
    private final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    private static final String TOPIC = "products";

    @Autowired
    private ProductService productService;

    private Gson gson = new Gson();

    @KafkaListener(topics = TOPIC, groupId = "group_id", autoStartup = "${kafka.consumer.start:false}")
    public void consume(String message) throws IOException {
        logger.info(String.format("#### -> Consumed message -> %s", message));
        Product product = gson.fromJson(message, Product.class);

        Product existingProduct = productService.findByProductId(product.getId());
        if(existingProduct == null) {
            productService.createProduct(product);
        } else {
            productService.updateProduct(product);
        }
        logger.info("#### -> Successfully processed message.");
    }
}
