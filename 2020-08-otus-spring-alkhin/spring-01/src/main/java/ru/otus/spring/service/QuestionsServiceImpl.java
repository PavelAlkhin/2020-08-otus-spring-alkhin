package ru.otus.spring.service;

import org.springframework.stereotype.Component;
import ru.otus.spring.dao.QuestionsDao;
import ru.otus.spring.domain.Question;

@Component(value = "QuestionsService")
public class QuestionsServiceImpl implements QuestionsService {

    private final QuestionsDao<Question> questionsDao;

    public QuestionsServiceImpl(QuestionsDao<Question> questionsDao) {
        this.questionsDao = questionsDao;
    }

    @Override
    public void addQuestion(Question question) {
        questionsDao.addQuestion(question);
    }

    @Override
    public Question readQuestion(int index){
        return questionsDao.findByIndex(index);
    }

    @Override
    public int totalNamberOfQuestions(){
        return questionsDao.lengthOfQuestionsList();
    }

}
