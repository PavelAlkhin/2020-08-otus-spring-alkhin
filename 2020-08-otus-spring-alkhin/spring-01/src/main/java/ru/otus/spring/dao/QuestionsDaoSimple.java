package ru.otus.spring.dao;

import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Question;

import java.util.ArrayList;
import java.util.List;

//@Repository
public class QuestionsDaoSimple implements QuestionsDao<Question> {

    private List<Question> questions = new ArrayList<Question>();

    public QuestionsDaoSimple() {
    }

    @Override
    public Question findByIndex(int index) {
        return questions.get(index);
    }

    @Override
    public void addQuestion(Question question) {
        questions.add(question);
    }

    @Override
    public int lengthOfQuestionsList() {
        return questions.size();
    }
}
