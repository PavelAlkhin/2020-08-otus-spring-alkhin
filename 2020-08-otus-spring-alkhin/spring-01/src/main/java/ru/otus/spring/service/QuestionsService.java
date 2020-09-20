package ru.otus.spring.service;

import ru.otus.spring.domain.Question;

public interface QuestionsService {

    void addQuestion(Question question);

    Question readQuestion(int index);

    int totalNamberOfQuestions();

    void deleteAllQuestions();

}