package ru.otus.spring.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Genre {

    @Setter @Getter
    private long id;

    private final String name;

    public Genre(String name) {
        this.name = name;
    }
}
