package ru.otus.spring.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.models.Genre;

import java.util.List;

public interface GenreRepository extends CrudRepository<Genre, Long> {
    Genre findByName(String name);
    List<Genre> findAllByIdIn(List<String> id);
}
