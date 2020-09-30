package ru.otus.spring.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author.getName() +'\'' +
                ", genre='" + genre.getName() +'\'' +
                '}';
    }
}
