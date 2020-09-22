package ru.otus.spring.dao;

import org.springframework.stereotype.Component;
import ru.otus.spring.domain.Student;

@Component
public class StudentDaoSimple implements StudentDao{

    @Override
    public Student addStudent(String name, String surname) {
        return new Student(name, surname);
    }
}
