package ru.otus.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.otus.spring.dao.StudentDao;
import ru.otus.spring.service.TestingService;
import ru.otus.spring.service.TestingServiceImpl;

@Configuration
@Import(StudentDaoConfig.class)
public class TestingServiceConfig {

    @Bean
    public TestingService testingService(StudentDao studentDao){
        return new TestingServiceImpl(studentDao);
    }
}
