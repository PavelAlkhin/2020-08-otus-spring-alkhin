package ru.otus.spring.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.CrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.models.Author;

import java.util.List;

public interface AuthorRepositoryWebFlux extends ReactiveMongoRepository<Author, String> {
    Mono<Author> findByName(String name);

    Flux<Author> findAllByIdIn(List<String> ids);
}
