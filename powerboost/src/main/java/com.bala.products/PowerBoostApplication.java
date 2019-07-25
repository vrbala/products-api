package com.bala.products;

import com.bala.products.service.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class PowerBoostApplication {

    @Autowired
    private KafkaConsumer kafkaConsumer;

    public static void main(String[] args) {

        SpringApplication.run(PowerBoostApplication.class, args);
    }

}
