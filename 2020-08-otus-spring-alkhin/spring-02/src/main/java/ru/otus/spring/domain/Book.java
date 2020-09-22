package ru.otus.spring.domain;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

//@RequiredArgsConstructor
@Data
public class Book {

    @Setter
    @Getter
    private long id;

    private final String title;

    private final Author author;

    private final Genre genre;

    public Book(String title, Author author, Genre genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
    }
}
