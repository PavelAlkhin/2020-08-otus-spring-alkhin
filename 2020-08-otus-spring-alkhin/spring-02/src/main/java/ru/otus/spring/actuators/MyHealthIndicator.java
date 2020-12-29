package ru.otus.spring.actuators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;
import ru.otus.spring.models.Book;
import ru.otus.spring.repositories.BookRepository;

import java.util.List;
import java.util.Random;

@Component
public class MyHealthIndicator implements HealthIndicator {

    @Autowired
    BookRepository bookRepository;

    @Override
    public Health health() {
        long books = bookRepository.count();
        if (books == 0) {
            return Health.up()
                    .status(Status.DOWN)
                    .withDetail("message", "App is up")
                    .build();
        } else {
            return Health.up().withDetail("message", "App is down!").build();
        }
    }
}
