package ru.otus.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.models.Author;
import ru.otus.spring.models.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

   List<Book> findByTitle(String title);

   List<Book> findByAuthorsContains(Author author);

   @Transactional
   List<Book> findAll();

   @SuppressWarnings("unchecked")
   Book save(Book book);

}
