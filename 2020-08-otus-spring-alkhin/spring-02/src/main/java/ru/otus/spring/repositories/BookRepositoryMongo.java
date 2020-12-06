package ru.otus.spring.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.models.mongo.BookMongo;

import java.util.List;

public interface BookRepositoryMongo extends CrudRepository<BookMongo, String> {
    List<BookMongo> findByTitle(String title);
}
