package ru.otus.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.dao.AuthorDaoJdbc;
import ru.otus.spring.dao.BookDaoJdbc;
import ru.otus.spring.dao.GenreDaoJdbc;
import static org.assertj.core.api.Assertions.assertThat;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("сервис для работы с книгами должно")
@JdbcTest
@Import({BookDaoJdbc.class, AuthorDaoJdbc.class, GenreDaoJdbc.class, BookServiceImpl.class, ScannerService.class})
class BookServiceImplTest {

    private static final int COUNT_BOOKS = 2;

    @Autowired
    BookServiceImpl bookService;

    @DisplayName("сохранить книгу в БД")
    @Test
    void saveBook() throws SQLException {
        bookService.saveBook("new book", "new author", "new genre");
        assertThat(bookService.countBooks()).isEqualTo(COUNT_BOOKS+1);
    }

    @DisplayName("посчитать количество книг в БД")
    @Test
    void countBooks() {
        assertThat(bookService.countBooks()).isEqualTo(COUNT_BOOKS);
    }

}