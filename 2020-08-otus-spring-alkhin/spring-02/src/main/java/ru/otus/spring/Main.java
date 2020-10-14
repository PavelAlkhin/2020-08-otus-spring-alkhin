package ru.otus.spring;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import ru.otus.spring.service.BookService;

import javax.annotation.PostConstruct;
import java.sql.SQLException;

@EnableMongock
@EnableMongoRepositories
//@Configuration
@SpringBootApplication
//@EnableJpaRepositories
public class Main {

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(Main.class, args);
    }

    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    BookService bookService;

    @PostConstruct
    public void fillInBooks(){

        //bookService.saveBook("Book1", "Author1", "Genre1", "Description1");
        //bookService.printAllBooks();

    }
}
