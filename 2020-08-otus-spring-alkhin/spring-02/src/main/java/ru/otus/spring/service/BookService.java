package ru.otus.spring.service;

import ru.otus.spring.models.Book;

import java.util.List;

public interface BookService {
    void saveBook(String title, String author, String genre, String description);
    int countBooks();
    void printAllBooks();
    List<Book> findByTite(String title);
}
