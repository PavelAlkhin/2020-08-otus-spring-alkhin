package ru.otus.spring.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Class Student")
@SpringBootTest
class StudentTest {

    Student student = new Student("name","surname");

    @DisplayName("Проверка установки конструктора и получения ФИО")
    @Test
    void getFio() {
         assertEquals("name surname", student.getFio());
    }

    @DisplayName("Проверка установки и получения количества сданных тестов")
    @Test
    void setNumberCorrectAnswers() {
        student.setNumberCorrectAnswers(5);
        assertEquals(5, student.getNumberCorrectAnswers());
    }

    @DisplayName("Проверка установки и получения оценки")
    @Test
    void getAndSetRating() {
        student.setRating(3);
        assertEquals(3, student.getRating());
    }

}