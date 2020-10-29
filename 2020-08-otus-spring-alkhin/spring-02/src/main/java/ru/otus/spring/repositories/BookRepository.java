package ru.otus.spring.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.models.Author;
import ru.otus.spring.models.Book;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, String> {

   List<Book> findByTitle(String title);

   List<Book> findByAuthorsContains(Author author);

   List<Book> findAll();

}
