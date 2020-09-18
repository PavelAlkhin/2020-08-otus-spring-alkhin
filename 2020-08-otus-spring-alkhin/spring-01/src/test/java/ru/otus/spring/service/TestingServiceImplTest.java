package ru.otus.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import ru.otus.spring.config.Props;
import ru.otus.spring.dao.StudentDao;
import ru.otus.spring.domain.Student;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class TestingServiceImplTest {

    @MockBean
    private StudentDao studentDao;

    @MockBean
    private Props props;

    @MockBean
    private QuestionsService questionsService;

    @MockBean
    private MessageSource messageSource;

    @MockBean
    private InputOutputService inputOutput;

    private TestingService testingService;

    @DisplayName("Создает студента")
    @Test
    void shouldAddStudent() {
        testingService = new TestingServiceImpl(studentDao, questionsService, props, messageSource, inputOutput);

        given(studentDao.addStudent("name", "surname"))
                .willReturn(new Student("name", "surname"));

        assertThat(testingService.addStudent("name", "surname"))
                .isNotNull();
    }

    @DisplayName("Заполняет вопросы из файла")
    @Test
    void shouldFillInQuestions() {

        InputOutputService OutputService;
        String fileName;

        given(props.getFilename()).willReturn("questionsTest.csv");

        System.out.println(props.getFilename());

        testingService = new TestingServiceImpl(studentDao, questionsService, props, messageSource, inputOutput);

        int num = questionsService.totalNamberOfQuestions();
        assertThat(num)
                .isEqualTo(0);

    }
}