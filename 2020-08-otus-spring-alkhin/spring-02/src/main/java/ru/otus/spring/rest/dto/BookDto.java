package ru.otus.spring.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import ru.otus.spring.models.Author;
import ru.otus.spring.models.Book;
import ru.otus.spring.models.Comment;
import ru.otus.spring.models.Genre;

import java.util.List;

@Getter
@Setter
@SuppressWarnings("all")
@AllArgsConstructor
public class BookDto {

    private String id;

    private String title;

    private String description;

    private List<Author> authors;

    private List<Genre> genres;

    private List<Comment> comments;

    public BookDto(Book book, Author author, Genre genre) {
    }

    public static BookDto toDto(Book book) {
        return new BookDto(book.getId(), book.getTitle(), book.getDescription(), book.getAuthors(), book.getGenres(), book.getComments());
    }

}