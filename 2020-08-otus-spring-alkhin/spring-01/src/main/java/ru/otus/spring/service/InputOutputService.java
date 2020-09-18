package ru.otus.spring.service;

import java.io.*;

public interface InputOutputService {

    void fillInQuestions() throws IOException;

    void beginTesting() throws IOException;

}