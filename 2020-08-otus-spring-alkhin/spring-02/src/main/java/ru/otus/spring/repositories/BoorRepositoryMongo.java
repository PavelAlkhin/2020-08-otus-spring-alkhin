package ru.otus.spring.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.models.Book;

import java.util.List;

public interface BoorRepositoryMongo extends CrudRepository<Book, String> {
    List<Book> findAll();
}
