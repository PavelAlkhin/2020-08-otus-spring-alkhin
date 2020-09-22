package ru.otus.spring.dao;

import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Genre;

import java.util.List;

public interface GenreDao {

    int count();

    Genre save(Genre genre);

    Genre getById(long id);

    int getIdByName(String name);

    void update(Genre genre);

    void deleteById(long author_id);

    List<Genre> getAll();

}
