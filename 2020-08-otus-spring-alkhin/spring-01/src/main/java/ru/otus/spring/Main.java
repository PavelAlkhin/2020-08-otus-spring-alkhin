package ru.otus.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.spring.service.InputOutputService;
import ru.otus.spring.service.InputOutputServiceImpl;
import ru.otus.spring.service.QuestionsService;
import ru.otus.spring.service.TestingService;

import java.io.*;

@ComponentScan
@Configuration
@PropertySource("classpath:application.properties")
public class Main {

    public static void main(String[] args) throws IOException {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Main.class);

        QuestionsService questionsService = context.getBean(QuestionsService.class);
        TestingService testingService = context.getBean(TestingService.class);

        InputOutputService inputOutputService = new InputOutputServiceImpl(questionsService, testingService);

        inputOutputService.beginTesting();

        context.close();

    }

}