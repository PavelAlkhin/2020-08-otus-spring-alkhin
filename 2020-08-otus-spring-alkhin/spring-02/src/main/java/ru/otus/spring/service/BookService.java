package ru.otus.spring.service;

import ru.otus.spring.models.Author;
import ru.otus.spring.models.Book;

import java.util.List;

public interface BookService {
    void saveBook(String title, String author, String genre, String description);
    int countBooks();
    void printBooks(List<Book> books);
    void printAllBooks();
    List<Book> findByTite(String title);
    List<Book> getBooksByAuthorId(Author author);
    Author getAuthorById(Long id);
    List<Book> getBooksByAuthorName(String name);
    String printBooksByAuthorName(String name);
}
