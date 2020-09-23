package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.service.TestingServiceImpl;

@RequiredArgsConstructor
@ShellComponent
public class ApplicationEventsCommands {

    private final TestingServiceImpl testingService;

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
