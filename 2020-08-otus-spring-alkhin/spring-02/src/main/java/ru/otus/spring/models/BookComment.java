package ru.otus.spring.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "comments")
public class BookComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String text;

    public BookComment(String text) {
        this.text = text;
    }

    public BookComment(long id, String text) {
        this.id = id;
        this.text = text;
    }

    public BookComment() {

    }
}
