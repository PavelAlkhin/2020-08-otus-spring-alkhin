package ru.otus.spring.service;

import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import ru.otus.spring.Main;
import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.Student;

import java.io.*;
import java.util.Scanner;

public class InputOutputServiceImpl implements InputOutputService{

    private QuestionsService questionsService;
    private TestingService testingService;

    private static final Logger logger = LoggerFactory.getLogger(InputOutputServiceImpl.class);

    public InputOutputServiceImpl(QuestionsService questionsService, TestingService testingService) throws IOException {
        this.questionsService = questionsService;
        this.testingService = testingService;

        fillInQuestions();
    }

    @Override
    public void fillInQuestions() throws IOException {
        InputStream stream = Main.class.getClassLoader().getResourceAsStream(questionsService.getQuestionsFile());

        Reader reader = new InputStreamReader(stream);
        BufferedReader bufferedReader = new BufferedReader(reader);

        String readLine;

        while ((readLine = bufferedReader.readLine()) != null) {
            String arr[] = readLine.split(";");
            questionsService.addQuestion(new Question(arr[0], arr[1]));
        }

        //logger.info( ,"Questions was read");

        stream.close();
    }

    @Override
    public void beginTesting() throws IOException {
        Scanner scaner = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String name = scaner.nextLine();

        System.out.print("Enter your surname: ");
        String surname = scaner.nextLine();

        Student student = testingService.addStudent(name, surname);

        for (int i = 0; questionsService.totalNamberOfQuestions() > i; i++) {
            askQuestion(scaner, questionsService.readQuestion(i), student, i);
        }

        System.out.println("Student " + student.getFio() + " was tested with an assessment " + student.getRating());

        scaner.close();

    }

    private void askQuestion(Scanner scaner, Question question, Student student, int index){

        System.out.print(index+1 + "." + question.getQuestion()+ " ");
        String textAnswer = scaner.nextLine();

        if (question.getAnswer().equals(textAnswer)){
            int correctAnswers = student.getNumberCorrectAnswers();
            student.setNumberCorrectAnswers(correctAnswers+1);
        }

    }
}

