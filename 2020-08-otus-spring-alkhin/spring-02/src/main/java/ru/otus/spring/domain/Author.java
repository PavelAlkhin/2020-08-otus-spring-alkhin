package ru.otus.spring.domain;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

//@RequiredArgsConstructor
@Data
public class Author {

    @Getter @Setter
    private long id;

    private final String name;

    public Author(String name) {
        this.name = name;
    }
}
