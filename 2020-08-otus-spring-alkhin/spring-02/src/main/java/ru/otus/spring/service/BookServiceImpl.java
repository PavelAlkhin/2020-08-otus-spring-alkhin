package ru.otus.spring.service;

import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import ru.otus.spring.models.Author;
import ru.otus.spring.models.Book;
import ru.otus.spring.models.Genre;
import ru.otus.spring.repositories.BookRepositoryJpa;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Service(value = "BookService")
public class BookServiceImpl implements BookService{

    private final BookRepositoryJpa bookRepositoryJpa;

    @Override
    public void saveBook(String title, String author, String genre, String description) {
        val authors = Collections.singletonList(new Author(0, author));
        val genres = Collections.singletonList(new Genre(0, genre));
        bookRepositoryJpa.save(new Book(0, title, description, authors, genres));
    }

    @Override
    public int countBooks() {
        return bookRepositoryJpa.countBooks();
    }

    @Override
    public void printAllBooks() {
        bookRepositoryJpa.printAllBooks();
    }

    @Override
    public List<Book> findByTite(String title) {
        return bookRepositoryJpa.findByTitle(title);
    }
}