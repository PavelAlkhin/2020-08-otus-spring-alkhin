package ru.otus.spring.dao;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.spring.domain.Question;

import java.util.ArrayList;
import java.util.List;

@Component
@NoArgsConstructor
public class QuestionsDaoSimple implements QuestionsDao<Question> {

    private final List<Question> questions = new ArrayList<>();

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
