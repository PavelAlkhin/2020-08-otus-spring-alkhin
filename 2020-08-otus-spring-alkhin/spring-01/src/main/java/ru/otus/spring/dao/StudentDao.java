package ru.otus.spring.dao;

import ru.otus.spring.domain.Student;

public interface StudentDao {
    Student addStudent(String name, String surname);
}