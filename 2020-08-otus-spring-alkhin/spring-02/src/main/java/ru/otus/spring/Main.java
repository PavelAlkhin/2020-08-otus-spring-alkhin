package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

@Configuration
@SpringBootApplication
public class Main {

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(Main.class, args);
    }
}
