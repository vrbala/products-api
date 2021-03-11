package com.bala.products.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationConfig {
    @Value("${ultraboost.create_product_async}")
    public boolean create_async;

    /**
     * Accepted persistence delay when sending messages through async mode
     * In milliseconds
     * Needs to be set based on benchmarking and acceptable performance standards.
     * */
    @Value(("${ultraboost.persistence_delay}"))
    public int persistence_delay;
}
