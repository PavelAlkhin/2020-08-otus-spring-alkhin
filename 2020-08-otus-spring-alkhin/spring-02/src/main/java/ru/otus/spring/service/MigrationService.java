package ru.otus.spring.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.models.Author;
import ru.otus.spring.models.Book;
import ru.otus.spring.models.Genre;
import ru.otus.spring.models.mongo.BookMongo;
import ru.otus.spring.repositories.BookRepository;
import ru.otus.spring.repositories.BookRepositoryMongo;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MigrationService {

    private BookRepositoryMongo repBookMongo;
    private BookRepository repBookJpa;

    public Book mapToBook(BookMongo bookMongo) {
        List<Author> authors = new ArrayList<>();
        bookMongo.getAuthors().forEach(a->authors.add(new Author(a.getName())));

        List<Genre> genres = new ArrayList<>();
        bookMongo.getGenres().forEach(g->genres.add(new Genre(g.getName())));

        return new Book(bookMongo.getTitle(), bookMongo.getDescription(), authors, genres);
    }
}
