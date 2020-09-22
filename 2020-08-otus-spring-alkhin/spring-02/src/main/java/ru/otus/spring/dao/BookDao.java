package ru.otus.spring.dao;

import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;

import java.sql.SQLException;
import java.util.List;

public interface BookDao {

    int count();

    Book save(Book book) throws SQLException;

    Book getBookById(long book_id);

    void update(Book book);

    List<Book> getAll();

    void deleteById(long book_id);
}
