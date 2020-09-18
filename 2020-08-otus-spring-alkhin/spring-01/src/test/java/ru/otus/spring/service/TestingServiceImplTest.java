package ru.otus.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.dao.StudentDao;
import ru.otus.spring.domain.Student;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class TestingServiceImplTest {

    @Mock
    private StudentDao studentDao;

    private TestingService testingService;

    @DisplayName("Создает студента")
    @Test
    void addStudent() {
        testingService = new TestingServiceImpl(studentDao);

        given(studentDao.addStudent("name", "sername"))
                .willReturn(new Student("name", "sername"));

        assertThat(testingService.addStudent("name", "sername"))
                .isNotNull();
    }
}