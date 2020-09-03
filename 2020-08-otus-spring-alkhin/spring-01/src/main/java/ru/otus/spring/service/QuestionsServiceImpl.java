package ru.otus.spring.service;

import ru.otus.spring.dao.QuestionsDao;
import ru.otus.spring.domain.Question;

public class QuestionsServiceImpl implements QuestionsService {

    private String questionsFile;
    private QuestionsDao<Question> questions;

    public QuestionsServiceImpl(QuestionsDao<Question> questions) {
        this.questions = questions;
    }

    // for property bean, filename
    public void setQuestionsFile(String questionsFile) {
        this.questionsFile = questionsFile;
    }

    @Override
    public void addQuestion(Question question) {
        questions.addQuestion(question);
    }

    @Override
    public String getQuestionsFile() { return questionsFile; }

    @Override
    public void printAllQuestions() {
        for (int i = 0; questions.lengthOfQuestionsList() > i; i++) {
            Question question = questions.findByIndex(i);
            System.out.println("Question: " + question.getQuestion() + ". Answer: " + question.getAnswer());
        }
    }
}
