package ru.otus.spring.service;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.repositories.BookRepositoryJpa;
import ru.otus.spring.repositories.BookRepositoryJpaImpl;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Сервис для работы с книгами ")
@DataJpaTest
@Import(BookRepositoryJpaImpl.class)
class BookServiceImplTest {

    @Autowired
    private BookRepositoryJpaImpl bookRepositoryJpa;


    @DisplayName("должен сохранить новую книгу")
    @Test
    void saveBook() {
    }

    @DisplayName("должен посчитать книги")
    @Test
    void countBooks() {
        System.out.println(bookRepositoryJpa.countBooks());
    }


    @DisplayName("должен найти все книги по названию")
    @Test
    void findByTite(){
        val books = bookRepositoryJpa.findByTitle("Name");
    }
}