package ru.otus.spring.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.otus.spring.models.Genre;
import ru.otus.spring.models.mongo.GenreMongo;

import java.util.List;

public interface GenreRepositoryMongo extends CrudRepository<GenreMongo, String> {

    GenreMongo findByName(String name);

    List<GenreMongo> findAllByIdIn(List<Long> id);

    GenreMongo save(@Param("genre") Genre genre);

}
