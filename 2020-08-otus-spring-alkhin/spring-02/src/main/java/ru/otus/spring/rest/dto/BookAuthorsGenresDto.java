package ru.otus.spring.rest.dto;

import lombok.Getter;
import lombok.Setter;
import ru.otus.spring.models.Author;
import ru.otus.spring.models.Book;
import ru.otus.spring.models.Genre;

import java.util.List;

@Getter
@Setter
public class BookAuthorsGenresDto {
    private Book book;
    private List<Author> authorList;
    private List<Genre> genreList;

    public BookAuthorsGenresDto() {
    }

    public BookAuthorsGenresDto(Book book, List<Author> authorList, List<Genre> genreList) {
        this.book = book;
        this.authorList = authorList;
        this.genreList = genreList;
    }

}
