package ru.otus.spring.dao;

import ru.otus.spring.domain.Author;

import java.util.List;

public interface AuthorDao {

    int count();

    Author save(Author author);

    Author getById(long author_id);

    int getIdByName(String name);

    void update(Author author);

    void deleteById(long author_id);

    List<Author> getAll();

}
