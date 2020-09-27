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
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Репозиторий на основе Jpa для работы с книгами ")
@DataJpaTest
@Import(BookRepositoryJpaImpl.class)
class BookRepositoryJpaImplTest {

    @Autowired
    private BookRepositoryJpaImpl repositoryJpa;

    @Autowired
    private TestEntityManager em;

    @Test
    void sholdFindById() {
        val firstbook = em.find(Book.class, 1L);
        Optional<Book> book = repositoryJpa.findById(1L);
        System.out.println(book);
        System.out.println(firstbook);

    }

    @Test
    void sholdsave() {
        val author = new Author(10,"New Author name");
        val authors = Collections.singletonList(author);
        val genre = new Genre(10,"New Genre book");
        val genres = Collections.singletonList(genre);

        val book = new Book(10,"Titlу of some new book", authors, genres);
        repositoryJpa.save(book);
        //assertThat(book.getId()).isGreaterThan(0);

        //val actualBook = em.find(Book.class, book.getId());

       // System.out.println(actualBook);


    }
}