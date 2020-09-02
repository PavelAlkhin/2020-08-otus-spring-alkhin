package ru.otus.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.domain.Question;
import ru.otus.spring.service.QuestionsService;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        QuestionsService questionsService = context.getBean(QuestionsService.class);

        getQuestionsFromFile(questionsService);

        questionsService.printAllQuestions();

        context.close();

    }

    public static void getQuestionsFromFile(QuestionsService questionsService) throws IOException {

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