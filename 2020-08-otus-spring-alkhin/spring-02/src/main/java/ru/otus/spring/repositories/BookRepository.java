package ru.otus.spring.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.models.Author;
import ru.otus.spring.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Long> {

    Book save(Book book);

    Optional<Book> findById(long id);

    List<Book> findAll();

    List<Book> findByTitle(String title);

    List<Book> findBooksByAuthorsContains(Author author);

//    void updateTitleById(long id, String title);

    void deleteById(long id);

    long count();

//    void printAllBooks();

//    void printBooks(List<Book> listBooks);

//    List<Book> findBooksByAuthors(Author author);

//    Author findAuthorById(Long id);

//    List<Book> findByAuthorsuthors_Name(String name);

}
