package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import ru.otus.spring.config.Props;
import ru.otus.spring.service.InputOutputService;
import ru.otus.spring.service.TestingService;

import java.io.IOException;

@Configuration
@SpringBootApplication
@EnableConfigurationProperties(Props.class)
public class Application {

//    @Bean(name = "applicationEventMulticaster")
//    public ApplicationEventMulticaster applicationEventMulticaster() {
//        SimpleApplicationEventMulticaster eventMulticaster = new SimpleApplicationEventMulticaster();
//        eventMulticaster.setTaskExecutor(new SimpleAsyncTaskExecutor());
//        return eventMulticaster;
//    }

    public static void main(String[] args) throws IOException {

//        AnnotationConfigApplicationContext context =
         ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);

        InputOutputService inputOutputService = new InputOutputService();

        TestingService testingService = ctx.getBean(TestingService.class);

        testingService.fillInQuestions(inputOutputService);
        testingService.beginTesting(inputOutputService);

    }

}