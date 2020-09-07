package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.QuestionsDao;
import ru.otus.spring.domain.Question;

@Service(value = "QuestionsService")
@PropertySource("classpath:application.properties")
public class QuestionsServiceImpl implements QuestionsService {

    @Value("${questions.filename}")
    private String questionsFile;
    private QuestionsDao<Question> questions;

    @Autowired
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

    @Override
    public Question readQuestion(int index){
        return questions.findByIndex(index);
    }

    @Override
    public int totalNamberOfQuestions(){
        return questions.lengthOfQuestionsList();
    }
}
