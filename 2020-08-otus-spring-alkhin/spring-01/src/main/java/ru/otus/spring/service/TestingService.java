package ru.otus.spring.service;

import ru.otus.spring.domain.Student;

import java.io.IOException;

public interface TestingService {

    Student addStudent(String name, String surname);

    void beginTesting() throws IOException;

}
