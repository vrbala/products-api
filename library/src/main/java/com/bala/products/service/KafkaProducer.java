package com.bala.products.service;

import com.bala.products.dto.Product;

public interface KafkaProducer {
    public void sendMessage(Product product);
}
