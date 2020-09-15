package ru.otus.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class StudentDaoSimpleTest {

    @DisplayName("Должен создавать студента")
    @Test
    void shoudaddStudent() {
        StudentDaoSimple studentDaoSimple = new StudentDaoSimple();
        var student = studentDaoSimple.addStudent("name","surname");
        assertThat(student.getFio()).isEqualTo("name surname");
    }
}