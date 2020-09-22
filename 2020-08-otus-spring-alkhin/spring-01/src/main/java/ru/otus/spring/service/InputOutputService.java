package ru.otus.spring.service;

import java.io.IOException;
import java.util.List;

public interface InputOutputService {
    public List<String[]> getQuestionsFromFile(String fileName) throws IOException;
    public String askQuestion(String questionText);
}
