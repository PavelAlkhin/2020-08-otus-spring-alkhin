package ru.otus.spring.dao;

import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Student;

@Repository
public class StudentDaoSimple implements StudentDao{

    Student student;

    @Override
    public Student addStudent(String name, String surname) {
        return new Student(name, surname);
    }

    @Override
    public void setRating(int rating) {
        student.setRating(rating);

    }

    @Override
    public void addCorrectAnswer() {
        student.setNumberCorrectAnswers(student.getNumberCorrectAnswers()+1);
    }

    @Override
    public int getRating() {
        return student.getRating();
    }

}
