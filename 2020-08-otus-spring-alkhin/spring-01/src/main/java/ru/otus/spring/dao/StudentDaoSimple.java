package ru.otus.spring.dao;

import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Student;

@Repository
public class StudentDaoSimple implements StudentDao{

    @Override
    public Student addStudent(String name, String surname) {
        return new Student(name, surname);
    }
}
