package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@SpringBootApplication
@EnableCircuitBreaker
public class ConsumerApp {
    public static void main(String[] args) {

        SpringApplication.run(ConsumerApp.class, args);

    }
}
