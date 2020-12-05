package ru.otus.spring.models.mongo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    public Author(String name) {
        this.name = name;
    }

    public Author() {

    }

    public Author(long id, String name) {
    }

    public static Author AuthorToDTO(Author author) {
        return new Author(author.getId(), author.getName());//, author.getBooks());
    }

}
