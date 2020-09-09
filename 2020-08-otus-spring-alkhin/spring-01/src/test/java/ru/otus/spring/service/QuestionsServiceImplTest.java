package ru.otus.spring.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.dao.QuestionsDao;
import ru.otus.spring.domain.Question;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class QuestionsServiceImplTest {

    @Mock
    private QuestionsDao questionsDao;

    private QuestionsService questionsService;

    @BeforeEach
    void setUp() {
        questionsService = new QuestionsServiceImpl(questionsDao);
    }

    @DisplayName("Устанавливает путь к файлу и получает его")
    @Test
    void setQuestionsFile() {
        questionsService.setQuestionsFile("questions.csv");
        assertThat(questionsService.getQuestionsFile())
                .isEqualTo("questions.csv");
    }

    @DisplayName("Добавляет вопрос, Получет вопрос")
    @Test
    void addQuestion() {

        Question question = new Question("quest1", "answer");

        given(questionsDao.findByIndex(0))
                .willReturn(question);

        assertThat(questionsService.readQuestion(0))
                .isEqualTo(question);

    }

    @DisplayName("Получет количество вопросов")
    @Test
    void totalNamberOfQuestions() {

        given(questionsDao.lengthOfQuestionsList())
                .willReturn(5);

        assertThat(questionsService.totalNamberOfQuestions())
                .isEqualTo(5);
    }
}