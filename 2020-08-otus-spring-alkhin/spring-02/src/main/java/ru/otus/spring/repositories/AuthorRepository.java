package ru.otus.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.models.Author;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Author findByName(String name);

    List<Author> findAllByIdIn(List<Long> ids);

    Author save( Author author );

}
