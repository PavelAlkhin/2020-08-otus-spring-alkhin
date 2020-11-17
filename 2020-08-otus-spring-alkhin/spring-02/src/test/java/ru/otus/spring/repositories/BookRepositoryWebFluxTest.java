package ru.otus.spring.repositories;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import ru.otus.spring.models.Book;

import static org.junit.jupiter.api.Assertions.*;

//@ExtendWith(SpringRunner.class)
@SpringBootTest
@DataMongoTest
class BookRepositoryWebFluxTest {

    @Autowired
    private BookRepositoryWebFlux bookRepository;

    @Test
    public void shouldFindByTitle() {
        Mono<Book> book = bookRepository.findByTitle("Book1");

        StepVerifier
                .create(book)
                .assertNext(book1 -> assertNotNull(book1.getTitle()))
                .expectComplete()
                .verify();
    }

    @Test
    public void findAll() {
        Flux<Book> bookFlux = bookRepository.findAll();

        StepVerifier
                .create(bookFlux)
                .assertNext(book1 -> assertNotNull(book1.getTitle()))
                .expectComplete()
                .verify();
    }
}