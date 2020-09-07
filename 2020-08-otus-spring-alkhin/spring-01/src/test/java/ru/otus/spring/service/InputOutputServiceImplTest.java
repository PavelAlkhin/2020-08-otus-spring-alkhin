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

import java.io.IOException;

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
        questionsService = new QuestionsServiceImpl(questions);
        testingService = new TestingServiceImpl(studentDao);
    }

    @Test
    void readQuestions() throws IOException {

        questionsService = new QuestionsServiceImpl(questions);
        testingService = new TestingServiceImpl(studentDao);

        InputOutputService inputOutputService = new InputOutputServiceImpl(questionsService, testingService);

        inputOutputService.fillInQuestions();

        assertThat(questionsService.getQuestionsFile()).isEqualTo("questions.csv");

    }
}