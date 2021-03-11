package com.bala.products.service;

import com.bala.products.dto.Product;
import com.google.gson.Gson;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerImpl implements KafkaProducer {
    private static final Logger logger = LoggerFactory.getLogger(KafkaProducerImpl.class);
    private static final String TOPIC = "products";
    private Gson gson = new Gson();

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(Product product) {
        String message = gson.toJson(product);
        ProducerRecord<String, String> pr = new ProducerRecord<>(TOPIC,
                product.getId(),
                message);
        logger.info(String.format("#### -> Producing message -> %s", pr));
        this.kafkaTemplate.send(pr);
    }
}
