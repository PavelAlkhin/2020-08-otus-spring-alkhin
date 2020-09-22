package ru.otus.spring.shell;

import lombok.SneakyThrows;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.service.TestingServiceImpl;


@ShellComponent
public class ApplicationEventsCommands {

    TestingServiceImpl testingService;

    public ApplicationEventsCommands(TestingServiceImpl testingService) {
        this.testingService = testingService;
    }

    @SneakyThrows
    @ShellMethod(value = "Start testing students", key = {"s", "start"})
    public String beginTestingStudents() {

        testingService.beginTesting();

        return "To start new test - type `s` or `start`";
    }

    @ShellMethod(value = "Count questions", key = {"count", "c"})
    public int countQustions() {
        return testingService.countQuestions();
    }

}
