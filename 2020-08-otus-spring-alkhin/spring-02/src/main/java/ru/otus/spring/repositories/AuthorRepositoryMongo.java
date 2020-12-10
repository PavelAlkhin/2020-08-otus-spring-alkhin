package ru.otus.spring.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.models.mongo.AuthorMongo;

public interface AuthorRepositoryMongo extends CrudRepository<AuthorMongo, String> {

    AuthorMongo findByName(String name);

    AuthorMongo save( AuthorMongo author );

}
