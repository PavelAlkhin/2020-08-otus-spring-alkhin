//package ru.otus.spring.rest;
//
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.Import;
//import org.springframework.ui.Model;
//import ru.otus.spring.models.Book;
//import ru.otus.spring.repositories.AuthorRepository;
//import ru.otus.spring.repositories.BookRepository;
//import ru.otus.spring.repositories.GenreRepository;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//
//@RunWith(MockitoJUnitRunner.class)
//@Import({BookController.class})
//@DataMongoTest
//class BookControllerTest {
//
//    @Autowired
//    BookRepository repBook;
//
//    @Autowired
//    AuthorRepository repAuthor;
//
//    @Autowired
//    GenreRepository repGenre;
//
//    @Autowired
//    private BookController bookController;
//
//    private Model model;
//
//    @Test
//    void listPage() {
////        BookController bookController2 = new BookController(repBook, repAuthor, repGenre);
////        String result = bookController2.listPage(model);
////        assertEquals("/", result);
//    }
//
//    @Test
//    void editPage() {
//    }
//
//    @Test
//    void saveBook() {
//    }
//
//    @Test
//    void newbook() {
//    }
//
//    @Test
//    void addNewbook() {
//    }
//}