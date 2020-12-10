package ru.otus.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import ru.otus.spring.models.Author;
import ru.otus.spring.models.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

   @PostFilter("hasPermission(filterObject, 'READ')")
   List<Book> findByTitle(String title);

   @PostFilter("hasPermission(filterObject, 'READ')")
   List<Book> findByAuthorsContains(Author author);

   @PostFilter("hasPermission(filterObject, 'READ')")
   List<Book> findAll();

   @SuppressWarnings("unchecked")
   @PreAuthorize("hasPermission(#book, 'WRITE')") //@Param("book")
   Book save(Book book);

}
