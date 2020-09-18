package ru.otus.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.dao.QuestionsDao;
import ru.otus.spring.dao.QuestionsDaoSimple;
import ru.otus.spring.domain.Question;

@Configuration
public class QuestionsDaoConfig {

    @Bean("questionsDao")
    public QuestionsDao<Question> questionsDao() {
        return new QuestionsDaoSimple();
    }
}
