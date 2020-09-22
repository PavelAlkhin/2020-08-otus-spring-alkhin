package ru.otus.spring.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Class Question")
@SpringBootTest
class QuestionTest {

    @DisplayName("Constructor")
    @Test
    void shouldHaveCorrectConstructor() {
        Question question = new Question("question", "answer");

        assertAll("question",
                () -> assertEquals("question", question.getQuestion()),
                () -> assertEquals("answer", question.getAnswer()));
    }

}