package ru.otus.spring.repositories;

import ru.otus.spring.models.Author;
import ru.otus.spring.models.Book;

import java.util.List;

public interface AuthorRepositoryJpa {
    List<Author> findAll();
    List<Author> findByName(String name);
    List<Book> findByAuthorName(String name);
    String printBooksByAuthorName(String name);
}
