package ru.otus.spring.service;

import ru.otus.spring.Application;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputOutputService {

    private final Scanner scaner = new Scanner(System.in);

    public List<String[]> getQuestionsFromFile(String fileName) throws IOException {

        InputStream stream = Application.class.getClassLoader().getResourceAsStream(fileName);

        Reader reader = new InputStreamReader(stream);
        BufferedReader bufferedReader = new BufferedReader(reader);

        List<String[]> listString = new ArrayList<>();
        String readLine;

        while ((readLine = bufferedReader.readLine()) != null) {
            String arr[] = readLine.split(";");
            listString.add(arr);
        }

        stream.close();

        return listString;
    }


    public String askQuestion(String questionText){
        System.out.print(questionText);
        String textAnswer = scaner.nextLine();
        return textAnswer;
    }


}
