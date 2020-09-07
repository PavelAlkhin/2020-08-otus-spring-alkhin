package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.dao.StudentDao;
import ru.otus.spring.domain.Student;

@Service(value = "TestingService")
public class TestingServiceImpl implements TestingService {

    private final StudentDao studentDao;

    public TestingServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public Student addStudent(String name, String surname) {
        return studentDao.addStudent(name, surname);
    }

}
