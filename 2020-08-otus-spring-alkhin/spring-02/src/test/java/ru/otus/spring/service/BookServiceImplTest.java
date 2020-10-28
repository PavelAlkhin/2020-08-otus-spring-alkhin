package ru.otus.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import ru.otus.spring.Main;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Сервис для работы с авторами и книгами")
@SpringBootTest
@Import({BookServiceImpl.class})
class BookServiceImplTest {

//    @Autowired
//    BookServiceImpl bookService;
//
//    @DisplayName("должен правильно посчитать книги")
//    @Test
//    void countBooks() {
//        assertThat(bookService.countBooks()).isEqualTo(12);
//    }
//
//    @DisplayName("должен найти книгу по титлу")
//    @Test
//    void findByTite() {
//        assertThat(bookService.findByTite("Book1")).hasSize(1);
//    }
//
//    @DisplayName("должен найти книги по имени автора")
//    @Test
//    void getBooksByAuthorName() {
//        assertThat(bookService.getBooksByAuthorName("Pushkin")).hasSize(8);
//    }


}