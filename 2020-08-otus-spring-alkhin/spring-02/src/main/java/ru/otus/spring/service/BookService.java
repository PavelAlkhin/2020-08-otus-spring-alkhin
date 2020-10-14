package ru.otus.spring.service;

import ru.otus.spring.models.Author;
import ru.otus.spring.models.Book;

import java.util.List;

public interface BookService {
    void saveBook(String title, List<String> authorsList, List<String> genre, String description);
    int countBooks();
    void printBooks(Iterable<Book> books);
    void printAllBooks();
    List<Book> findByTite(String title);
    List<Book> getBooksByAuthorName(String name);
    void printBooksByAuthorName(String name);
    void readListFromScanner(List<String> answerList, String enterType);
    int countAuthors();
    void printAllAuthors();
}
