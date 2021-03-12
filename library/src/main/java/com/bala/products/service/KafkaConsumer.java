package com.bala.products.service;

import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface KafkaConsumer {
    public void consume(String message) throws IOException;
}
