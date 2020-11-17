package ru.otus.spring.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.CrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.models.Author;
import ru.otus.spring.models.Genre;

import java.util.List;

public interface GenreRepositoryWebFlux extends ReactiveMongoRepository<Genre, String> {
    Mono<Genre> findByName(String name);
    Flux<Genre> findAllByIdIn(List<String> ids);

}
