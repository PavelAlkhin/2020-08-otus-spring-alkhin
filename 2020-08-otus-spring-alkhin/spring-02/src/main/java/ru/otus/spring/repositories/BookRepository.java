package ru.otus.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.otus.spring.models.Author;
import ru.otus.spring.models.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

   //   @PostFilter("hasPermission(filterObject, 'READ')")
   @RestResource(path="title" , rel="title")
   List<Book> findByTitle(String title);

//   @PostFilter("hasPermission(filterObject, 'READ')")
   List<Book> findByAuthorsContains(Author author);

//   @PostFilter("hasPermission(filterObject, 'READ')") List<Book> findAll();

   @SuppressWarnings("unchecked")
//   @PreAuthorize("hasPermission(#book, 'WRITE')") //@Param("book")
   Book save(Book book);

}
