package com.bala.products.service;

import java.io.IOException;

public interface KafkaConsumer {
    public void consume(String message) throws IOException;
}
