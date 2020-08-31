package ru.otus.spring.service;

import ru.otus.spring.domain.Question;

public interface QuestionsService {

    String getQuestionsFile();

    void addQuestion(Question question);

    void printAllQuestions();

}