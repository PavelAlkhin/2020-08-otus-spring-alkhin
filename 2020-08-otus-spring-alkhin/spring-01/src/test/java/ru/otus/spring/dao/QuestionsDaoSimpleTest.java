package ru.otus.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.domain.Question;

import static org.assertj.core.api.Assertions.assertThat;

class QuestionsDaoSimpleTest {

    @DisplayName("находит по индексу, добавленный вопрос")
    @Test
    void findByIndex() {
        QuestionsDaoSimple questionsDaoSimple = new QuestionsDaoSimple();
        questionsDaoSimple.addQuestion(new Question("question1", "1"));
        assertThat(questionsDaoSimple.findByIndex(0)).isNotNull();
    }

    @DisplayName("возвращает правильное количество элементов в массиве")
    @Test
    void lengthOfQuestionsList() {
        QuestionsDaoSimple questionsDaoSimple = new QuestionsDaoSimple();
        questionsDaoSimple.addQuestion(new Question("question2", "2"));
        assertThat(questionsDaoSimple.lengthOfQuestionsList()).isEqualTo(1);
    }
}