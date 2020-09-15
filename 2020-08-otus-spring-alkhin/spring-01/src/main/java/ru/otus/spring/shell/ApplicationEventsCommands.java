package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.service.InputOutputService;
import ru.otus.spring.service.TestingServiceImpl;

import java.io.IOException;


@ShellComponent
public class ApplicationEventsCommands {

    TestingServiceImpl testingService;

    public ApplicationEventsCommands(TestingServiceImpl testingService) {
        this.testingService = testingService;
    }

    @SneakyThrows
    @ShellMethod(value = "Start testing students", key = {"s", "start"})
    public String beginTestingStudents() {

        InputOutputService inputOutputService = new InputOutputService();

        testingService.fillInQuestions(inputOutputService);
        testingService.beginTesting(inputOutputService);

        return "To start new test - type `s` or `start`";
    }

}
