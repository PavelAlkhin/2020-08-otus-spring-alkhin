package ru.otus.spring.dao;

import ru.otus.spring.domain.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionsDaoSimple implements QuestionsDao<Question> {

    private List<Question> questions = new ArrayList<Question>();

    public QuestionsDaoSimple() {
    }

    @Override
    public Question findByIndex(int index) {
        return questions.get(index);
    }

    @Override
    public void addQuestion(Question o) {
        questions.add((Question) o);
    }

    @Override
    public int lengthOfQuestionsList() {
        return questions.size();
    }
}
