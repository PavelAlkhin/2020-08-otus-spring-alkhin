package ru.otus.spring.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.dao.QuestionsDao;
import ru.otus.spring.dao.QuestionsDaoSimple;
import ru.otus.spring.dao.StudentDao;
import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class InputOutputServiceImplTest {

    @Mock
    private QuestionsDao<Question> questions;

    private QuestionsService questionsService;

    @Mock
    private StudentDao studentDao;

    TestingService testingService;

    @BeforeEach
    void setUp() {

        questions.addQuestion(new Question("question1","1"));
        StudentDao stud = (StudentDao) studentDao.addStudent("name", "surname");

        questionsService = new QuestionsServiceImpl(questions);
        testingService = new TestingServiceImpl(stud);

    }

    @Test
    void readQuestions() throws IOException {

        InputOutputService inputOutputService = new InputOutputServiceImpl(questionsService, testingService);

        inputOutputService.fillInQuestions();

        assertThat(questionsService.getQuestionsFile()).isEqualTo("questions.csv");

    }
}