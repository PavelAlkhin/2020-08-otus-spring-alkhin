package ru.otus.spring.service;

import java.sql.SQLException;

public interface BookService {
    void saveBook(String title, String author, String genre) throws SQLException;
    int countBooks();
    void printAllBooks();
}
