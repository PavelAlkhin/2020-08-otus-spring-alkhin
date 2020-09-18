package ru.otus.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.otus.spring.dao.QuestionsDao;
import ru.otus.spring.domain.Question;
import ru.otus.spring.service.QuestionsServiceImpl;

@Configuration
@Import(QuestionsDaoConfig.class)
public class QuestionsServiceConfig {

    @Bean
    public QuestionsServiceImpl questionsService(QuestionsDao<Question> questionsDao) {
        return new QuestionsServiceImpl(questionsDao);
    }
}
