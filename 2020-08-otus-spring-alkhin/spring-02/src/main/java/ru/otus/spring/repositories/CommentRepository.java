package ru.otus.spring.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.models.Book;
import ru.otus.spring.models.Comment;

public interface CommentRepository extends CrudRepository<Comment, String> {
    Comment findAllByBook(Book book);
}
