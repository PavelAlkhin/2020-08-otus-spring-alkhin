package ru.otus.spring.repositories;

import lombok.val;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionFactoryImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring.models.Author;
import ru.otus.spring.models.Book;
import ru.otus.spring.models.Genre;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий на основе Jpa для работы с книгами ")
@DataJpaTest
@Import(BookRepositoryJpaImpl.class)
class BookRepositoryJpaImplTest {

    private static final long BOOK_ID = 1L;
    private static final String BOOK_TITLE = "Book1";

    @Autowired
    private BookRepositoryJpaImpl bookRepositoryJpa;

    @Autowired
    private TestEntityManager em;

    @DisplayName("должен найти книгу по id")
    @Test
    void sholdFindById() {
        val firstbook = em.find(Book.class, BOOK_ID);
        Optional<Book> book = bookRepositoryJpa.findById(BOOK_ID);
        assertThat(firstbook).isNotNull()
                .isEqualTo(book.get());
    }

    @DisplayName("должен сохранить новую книгу")
    @Test
    void shouldSave() {

        Author author = new Author(0L,"New Author name");
        List<Author> authors = Collections.singletonList(author);

        Genre genre = new Genre(0L,"New Genre book");
        List<Genre> genres = Collections.singletonList(genre);

        Book book = new Book(0L,"Titlу of some new book", "desc", authors, genres);

        bookRepositoryJpa.save(book);

        val actualBook = em.find(Book.class, book.getId());

        assertThat(book).isEqualTo(actualBook);

    }

    @DisplayName("должен найти все книги")
    @Test
    void shouldFindAll(){
        SessionFactory sessionFactory = em.getEntityManager().getEntityManagerFactory()
                .unwrap(SessionFactory.class);
        sessionFactory.getStatistics().setStatisticsEnabled(true);

        System.out.println("\n\n\n\n----------------------------------------------------------------------------------------------------------");

        val books = bookRepositoryJpa.findAll();

        val iterBooks = books.iterator();

        while (iterBooks.hasNext()){
            System.out.println(iterBooks.next().toString());
        }

        System.out.println("----------------------------------------------------------------------------------------------------------\n\n\n\n");

        assertThat(books.size()).isEqualTo(12);
    }

    @DisplayName("должен обновить название книги по id")
    @Test
    void shouldUpdateTitleById(){
        bookRepositoryJpa.updateNameById(BOOK_ID, "Titlу of some new book");
        val updatedBook = em.find(Book.class, BOOK_ID);
        assertThat(updatedBook.getTitle()).isEqualTo("Titlу of some new book");
    }

    @DisplayName("должен найти книгу по названию")
    @Test
    void shouldFindByTitle(){
        val firstBook = em.find(Book.class, 1L);
        List<Book> books = bookRepositoryJpa.findByTitle("Book1");
        assertThat(books).containsOnlyOnce(firstBook);
    }

    @DisplayName("должен удалить книгу по id")
    @Test
    void shouldDeleteById() {
        val cntBooks = bookRepositoryJpa.countBooks();
        bookRepositoryJpa.deleteById(BOOK_ID);
        val cntBooksExp = bookRepositoryJpa.countBooks();
        assertThat(cntBooksExp).isEqualTo(cntBooks-1);

    }

    @DisplayName("should find book by Author name")
    @Test
    void shouldgetBooksByAuthorName(){
        val books = bookRepositoryJpa.getBooksByAuthorName("Pushkin");
        bookRepositoryJpa.printBooks(books);
        assertThat(books.size()).isEqualTo(8);
    }


}