package ru.otus.spring.repositories;

import ru.otus.spring.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepositoryJpa {

    Book save(Book book);

    Optional<Book> findById(long id);

    List<Book> findAll();

    List<Book> findByTitle(String name);

    void updateNameById(long id, String name);

    void deleteById(long id);

    int countBooks();

    void printAllBooks();
}
