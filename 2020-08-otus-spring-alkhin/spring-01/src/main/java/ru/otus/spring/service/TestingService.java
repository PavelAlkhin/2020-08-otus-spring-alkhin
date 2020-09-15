package ru.otus.spring.service;

import ru.otus.spring.domain.Student;

import java.io.IOException;

public interface TestingService {

    Student addStudent(String name, String surname);

    void fillInQuestions(InputOutputService readQuestion) throws IOException;

    void beginTesting(InputOutputService readQuestion) throws IOException;

}
