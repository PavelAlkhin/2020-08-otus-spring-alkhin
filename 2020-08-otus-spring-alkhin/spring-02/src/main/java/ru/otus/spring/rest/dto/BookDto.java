package ru.otus.spring.rest.dto;

import lombok.Getter;
import lombok.Setter;
import ru.otus.spring.models.Author;
import ru.otus.spring.models.Book;
import ru.otus.spring.models.BookComment;
import ru.otus.spring.models.Genre;

import java.util.List;

@Getter
@Setter
public class BookDto {

    private long id;

    private String title;

    private String description;

    private List<Author> authors;

    private List<Genre> genres;

    private List<BookComment> comments;

    public BookDto(Book book, Author author, Genre genre) {
    }

    public BookDto(long id, String title, String description, List<Author> authors, List<Genre> genres, List<BookComment> comments) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.authors = authors;
        this.genres = genres;
        this.comments = comments;
    }
}
