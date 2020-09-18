package ru.otus.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.dao.QuestionsDao;
import ru.otus.spring.dao.QuestionsDaoSimple;
import ru.otus.spring.dao.StudentDao;
import ru.otus.spring.dao.StudentDaoSimple;
import ru.otus.spring.domain.Question;

@Configuration
public class StudentDaoConfig {

    @Bean("studentDao")
    public StudentDao studentDao() {
        return new StudentDaoSimple();
    }
}
