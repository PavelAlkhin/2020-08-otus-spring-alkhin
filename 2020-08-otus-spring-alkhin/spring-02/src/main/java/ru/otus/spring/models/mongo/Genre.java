package ru.otus.spring.models.mongo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "genres")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    public Genre(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Genre() {
    }
}
