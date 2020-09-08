package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.QuestionsDao;
import ru.otus.spring.domain.Question;

//@Service(value = "QuestionsService")
@PropertySource("classpath:application.properties")
public class QuestionsServiceImpl implements QuestionsService {

    @Value("${questions.filename}")
    private String questionsFile;

    private final QuestionsDao<Question> questionsDao;

//    @Autowired
    public QuestionsServiceImpl(QuestionsDao<Question> questionsDao) {
        this.questionsDao = questionsDao;
    }

    // for property bean, filename
    public void setQuestionsFile(String questionsFile) {
        this.questionsFile = questionsFile;
    }

    @Override
    public void addQuestion(Question question) {
        questionsDao.addQuestion(question);
    }

    @Override
    public String getQuestionsFile() {
        return questionsFile;
    }

    @Override
    public void printAllQuestions() {
        for (int i = 0; questionsDao.lengthOfQuestionsList() > i; i++) {
            Question question = questionsDao.findByIndex(i);
            System.out.println("Question: " + question.getQuestion() + ". Answer: " + question.getAnswer());
        }
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
