package ru.otus.spring.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.models.Book;

public interface BoorRepositoryMongo extends MongoRepository {
    List<Book> Fin
}
