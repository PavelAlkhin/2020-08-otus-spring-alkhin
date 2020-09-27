package ru.otus.spring.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import lombok.val;
import ru.otus.spring.models.Author;
import ru.otus.spring.models.Book;
import ru.otus.spring.models.Genre;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Репозиторий на основе Jpa для работы с книгами ")
@DataJpaTest
@Import(BookRepositoryJpaImpl.class)
class BookRepositoryJpaImplTest {

    @Autowired
    private BookRepositoryJpaImpl bookRepositoryJpa;

    @Autowired
    private TestEntityManager em;

    @Test
    void sholdFindById() {
        val firstbook = em.find(Book.class, 1L);
        Optional<Book> book = bookRepositoryJpa.findById(1L);
        assertThat(firstbook).isNotNull()
                .isEqualTo(book.get());
    }

    @Test
    void sholdsave() {

        Author author = new Author(0L,"New Author name");
        List<Author> authors = Collections.singletonList(author);

        Genre genre = new Genre(0L,"New Genre book");
        List<Genre> genres = Collections.singletonList(genre);

        Book book = new Book(0L,"Titlу of some new book", authors, genres);

        bookRepositoryJpa.save(book);

        assertThat(book.getId()).isGreaterThan(0L);

        val actualBook = em.find(Book.class, book.getId());

       // System.out.println(actualBook);


    }
}