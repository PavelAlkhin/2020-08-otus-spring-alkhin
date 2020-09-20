package ru.otus.spring.dao;


public interface QuestionsDao<Question> {

    Question findByIndex(int index);

    void addQuestion(Question question);

    int lengthOfQuestionsList();

    void clearList();

}
