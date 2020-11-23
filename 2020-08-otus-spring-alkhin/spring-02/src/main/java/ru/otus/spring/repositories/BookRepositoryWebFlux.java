package ru.otus.spring.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.CrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.models.Author;
import ru.otus.spring.models.Book;

import java.util.List;

public interface BookRepositoryWebFlux extends ReactiveMongoRepository<Book, String> {

   Mono<Book> findByTitle(String title);

   Flux<Book> findByAuthorsContains(Author author);

   Flux<Book> findAll();

}
