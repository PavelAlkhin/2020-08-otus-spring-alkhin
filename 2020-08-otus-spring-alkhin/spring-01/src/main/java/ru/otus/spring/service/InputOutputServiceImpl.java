package ru.otus.spring.service;

import ru.otus.spring.Main;
import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.Student;

import java.io.*;
import java.util.Scanner;

public class InputOutputServiceImpl implements InputOutputService{

    private QuestionsService questionsService;
    private TestingService testingService;

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

        stream.close();
    }

    @Override
    public void beginTesting() throws IOException {
        Scanner inName = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = inName.nextLine();

        Scanner inSurname = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String surname = inSurname.nextLine();

        Student student = testingService.addStudent(name, surname);

        for (int i = 0; questionsService.totalNamberOfQuestions() > i; i++) {
            askQuestion(questionsService.readQuestion(i), student, i);
        }

        System.out.println("Student " + student.getFio() + " was tested with an assessment " + student.getRating());

    }

    private void askQuestion(Question question, Student student, int index){

        Scanner scQuestion = new Scanner(System.in);
        System.out.print(index+1 + "." + question.getQuestion()+ " ");
        String textAnswer = scQuestion.nextLine();

        if (question.getAnswer().equals(textAnswer)){
            int correctAnswers = student.getNumberCorrectAnswers();
            student.setNumberCorrectAnswers(correctAnswers+1);
        }
    }
}

