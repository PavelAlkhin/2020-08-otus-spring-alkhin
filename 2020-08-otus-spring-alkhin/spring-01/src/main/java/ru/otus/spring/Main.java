package ru.otus.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.domain.Question;
import ru.otus.spring.service.QuestionsService;

import java.io.*;

@ComponentScan
@Configuration
public class Main {

    public static void main(String[] args) throws IOException {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Main.class);
        QuestionsService questionsService = context.getBean(QuestionsService.class);

        getQuestionsFromFile(questionsService);

        questionsService.printAllQuestions();

        context.close();

    }

    public static void getQuestionsFromFile(@org.jetbrains.annotations.NotNull QuestionsService questionsService) throws IOException {

        InputStream stream = Main.class.getClassLoader().getResourceAsStream(questionsService.getQuestionsFile());

        Reader reader = new InputStreamReader(stream);
        BufferedReader bufferedReader = new BufferedReader(reader);

        String readLine;

        while ((readLine = bufferedReader.readLine()) != null) {
            String arr[] = readLine.split(";");
            questionsService.addQuestion(new Question(arr[0], arr[1]));
        }

        stream.close();
    }
}