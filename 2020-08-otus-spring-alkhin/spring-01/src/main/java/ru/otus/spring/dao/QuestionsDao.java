package ru.otus.spring.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface QuestionsDao<Question> {

    Question findByIndex(int index);

    void addQuestion(Question question);

    int lengthOfQuestionsList();

}
