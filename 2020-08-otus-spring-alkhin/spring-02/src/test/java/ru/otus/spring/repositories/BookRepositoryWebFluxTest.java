package ru.otus.spring.repositories;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import ru.otus.spring.models.Book;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

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
    public void shouldFindAll() {
        Flux<Book> bookFlux = bookRepository.findAll();

        StepVerifier
                .create(bookFlux.collect(Collectors.toList()))
                .assertNext(book1 -> assertEquals(book1.size(),2))
                .expectComplete()
                .verify()
        ;
    }

    @Test
    public void shouldSaveBook(){
        bookRepository.save(new Book("3","333", new ArrayList<>(), new ArrayList<>())).subscribe();

        StepVerifier
                .create(bookRepository.findAll())
                .expectNextCount(3)
                .expectComplete()
                .verify()
        ;
    }
}