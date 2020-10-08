package ru.otus.spring.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.models.Author;
import ru.otus.spring.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Long> {

   List<Book> findByTitle(String title);

    List<Book> findBooksByAuthorsContains(Author author);

}
