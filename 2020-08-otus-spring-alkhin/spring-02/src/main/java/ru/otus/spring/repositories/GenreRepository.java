package ru.otus.spring.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.models.Genre;

public interface GenreRepository extends CrudRepository<Genre, String> {
}
