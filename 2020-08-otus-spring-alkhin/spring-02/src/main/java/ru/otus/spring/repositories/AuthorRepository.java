package ru.otus.spring.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.models.Author;

import java.util.List;

public interface AuthorRepository extends CrudRepository<Author, Long> {

    Author findByName(String name);

    List<Author> findAllByIdIn(List<Long> ids);

    Author save( Author author );

}
