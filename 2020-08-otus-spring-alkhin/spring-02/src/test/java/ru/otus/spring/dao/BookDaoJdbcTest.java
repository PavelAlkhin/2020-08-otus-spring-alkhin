package ru.otus.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Dao для работы с книгами должно")
@JdbcTest
@Import({BookDaoJdbc.class, AuthorDaoJdbc.class, GenreDaoJdbc.class})
//@Transactional(propagation = Propagation.NOT_SUPPORTED)
class BookDaoJdbcTest {

    private static final int EXPECTED_BOOKS_COUNT = 2;
    private static final long BOOK_ID = 1L;
    private static final String BOOK_NAME = "Scy my";
    private static final long AUTHOR_ID = 2;

    @Autowired
    private BookDaoJdbc bookDaoJdbc;

    @Autowired
    private AuthorDao authorDao;

    @Autowired
    private BookDao bookDao;

    @DisplayName("возвращает количество книг по титлу и автору")
    @Test
    void shouldcountByTitleAuthor() {
        int с = bookDaoJdbc.countByTitleAuthor(BOOK_NAME, AUTHOR_ID);
        assertThat(с).isEqualTo(1);
    }

    @DisplayName("возвращает количество книг")
    @Test
    void shouldcount() {
        int count = bookDaoJdbc.count();
        assertThat(count).isEqualTo(EXPECTED_BOOKS_COUNT);

    }

    @DisplayName("возвращает id книги по титлу и автору")
    @Test
    void shouldgetIdByTitleAuthor() {
        long id = bookDaoJdbc.getIdByTitleAuthor(BOOK_NAME, AUTHOR_ID);
        assertThat(id).isEqualTo(BOOK_ID);    }

    @DisplayName("сохраняет книгу в БД")
    @Test
    void shouldsave() throws SQLException {
        Book actBook = new Book("new book", new Author("new author"), new Genre("new genre"));
        actBook = bookDaoJdbc.save(actBook);
        assertThat(bookDaoJdbc.count()).isEqualTo(3);
    }

    @DisplayName("возвращает книгу по id")
    @Test
    void shouldgetBookById() {
        Book book = bookDaoJdbc.getBookById(BOOK_ID);
        assertThat(book)
                .hasFieldOrPropertyWithValue("id", BOOK_ID)
                .hasFieldOrPropertyWithValue("title", BOOK_NAME);
    }

    @DisplayName("обновляет книгу по id")
    @Test
    void shouldupdate() {
        Book actBook = new Book("new book", new Author("new author"), new Genre("new genre"));
        actBook.setId(BOOK_ID);
        bookDaoJdbc.update(actBook);
        Book expBook = bookDaoJdbc.getBookById(BOOK_ID);
        assertThat(expBook.getTitle()).isEqualTo("new book");
    }

    @DisplayName("получает все книги")
    @Test
    void shouldgetAll() {
        List<Book> book = bookDaoJdbc.getAll();
        assertThat(book.size()).isEqualTo(EXPECTED_BOOKS_COUNT);
    }

    @DisplayName("удаляет книгу")
    @Test
    void shoulddeleteById() {
        bookDaoJdbc.deleteById(BOOK_ID);
        int count = bookDaoJdbc.count();
        assertThat(count).isEqualTo(EXPECTED_BOOKS_COUNT-1);
    }
}