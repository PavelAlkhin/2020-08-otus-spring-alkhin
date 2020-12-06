package ru.otus.spring.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.otus.spring.models.Genre;

import java.util.List;

public interface GenreRepository extends CrudRepository<Genre, Long> {

    Genre findByName(String name);

    List<Genre> findAllByIdIn(List<Long> id);

    Genre save(@Param("genre") Genre genre);

}
