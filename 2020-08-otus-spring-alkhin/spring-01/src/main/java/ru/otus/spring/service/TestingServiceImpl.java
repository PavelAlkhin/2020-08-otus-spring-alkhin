package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.otus.spring.config.Props;
import ru.otus.spring.dao.StudentDao;
import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.Student;

import java.io.IOException;
import java.util.List;

@Component(value = "TestingService")
@RequiredArgsConstructor
public class TestingServiceImpl implements TestingService {

    private final StudentDao studentDao;
    private final QuestionsService questionsService;
    private final Props props;
    private final MessageSource messageSource;
    private final InputOutputService inputOutput;

    private static final Logger logger = LoggerFactory.getLogger(TestingServiceImpl.class);

    private void fillInQuestions() throws IOException {

        List<String[]> listString = inputOutput.getQuestionsFromFile(props.getFilename());

        for(int i = 0; listString.size()>i; i++){
            String arr[] = listString.get(i);
            questionsService.addQuestion(new Question(arr[0], arr[1]));
        }
    }

    @Override
    public Student addStudent(String name, String surname) {
        return studentDao.addStudent(name, surname);
    }

    @Override
    public void beginTesting() throws IOException {

        logger.info("start fill in questions from file");
        fillInQuestions();
        logger.info("finish fill in questions from file");

        logger.info("bigin testing");
        String answerName = inputOutput.askQuestion(messageSource.getMessage("name", new String[] {""}, props.getLocale()));
        String answerSurname = inputOutput.askQuestion(messageSource.getMessage("surname", new String[] {""}, props.getLocale()));

        Student student = addStudent(answerName, answerSurname);

        for (int i = 0; questionsService.totalNamberOfQuestions() > i; i++) {

            String textAnswer = inputOutput.askQuestion(questionsService.readQuestion(i).getQuestion());

            if (questionsService.readQuestion(i).getAnswer().equals(textAnswer)){
                int correctAnswers = student.getNumberCorrectAnswers();
                student.setNumberCorrectAnswers(correctAnswers+1);
            }
        }

        String strResult = messageSource.getMessage("result", new String[] {student.getFio(), "" + student.getRating()}, props.getLocale());

        System.out.println(strResult);
        logger.info(strResult);
    }
}
