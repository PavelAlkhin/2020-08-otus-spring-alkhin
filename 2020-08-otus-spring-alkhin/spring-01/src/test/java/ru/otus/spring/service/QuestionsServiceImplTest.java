package ru.otus.spring.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.config.Props;
import ru.otus.spring.dao.QuestionsDao;
import ru.otus.spring.domain.Question;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class QuestionsServiceImplTest {

//    @MockBean
//    private QuestionsDao<Question> questionsDao;
//
//    @MockBean
//    private Props props;
//
//    private QuestionsService questionsService;
//
//    @BeforeEach
//    void setUp() {
//        questionsService = new QuestionsServiceImpl(questionsDao);
//    }

    @DisplayName("Добавляет вопрос, Получет вопрос")
    @Test
    void addQuestion() {

//        Question question = new Question("quest1", "answer");
//
//        given(questionsDao.findByIndex(0))
//                .willReturn(question);
//
//        assertThat(questionsService.readQuestion(0))
//                .isEqualTo(question);

    }

    @DisplayName("Получет количество вопросов")
    @Test
    void totalNamberOfQuestions() {

//        given(questionsDao.lengthOfQuestionsList())
//                .willReturn(5);
//
//        assertThat(questionsService.totalNamberOfQuestions())
//                .isEqualTo(5);
    }
}