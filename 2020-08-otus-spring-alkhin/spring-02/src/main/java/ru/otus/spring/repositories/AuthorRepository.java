package ru.otus.spring.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.models.Author;
import ru.otus.spring.models.Book;

import java.util.List;

public interface AuthorRepository extends CrudRepository<Author, String> {
    Author findByName(String name);
    List<Author> findAllByIdIn(List<String> ids);
}
