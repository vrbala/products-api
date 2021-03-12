package com.bala.products.service;

import com.bala.products.dto.Product;
import org.springframework.stereotype.Service;

@Service
public interface KafkaProducer {
    public void sendMessage(Product product);
}
