package ru.otus.spring;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DataAccessException;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Main {

    public static <authorList> void main(String[] args) throws Exception {

        ApplicationContext context = SpringApplication.run(Main.class);

//        BookDao dao = context.getBean(BookDao.class);

//        Book newBook = new Book("new book", new Author("new author"), new Genre("new genre"));

//        dao.save(newBook);

        AuthorDao authorDao = context.getBean(AuthorDao.class);

//        Author author = authorDao.save("1111");
//        Author author1 = authorDao.save("1111");

        //int id = authorDao.save(new Author("wind of changes"));

        //System.out.println(id);

        //id = authorDao.save(new Author("wind of changes"));

//        System.out.println(authorDao.readById(3));

//        int col = authorDao.countByName("wind of ккchanges");
//        System.out.println(author);
//        System.out.println(author1.equals(author));

        Author author = authorDao.save(new Author("refsj"));

        int author_id = 0;
        
        try {
            author_id = authorDao.getIdByName("refsj");
        }
        catch (DataAccessException e) {
            System.out.println("A problem occurred while retrieving author " + e);
        };

        System.out.println(author);
        System.out.println(author_id);

        BookDao book = context.getBean(BookDao.class);

        Book newBook = new Book("new book", new Author("new author"), new Genre("new genre"));

        book.save(newBook);


//        id = authorDao.save(new Author("wind of changes"));
//
//        System.out.println(id);
//
//        id = authorDao.save(new Author("wind of changes"));
//
//        System.out.println(id);

        //authorDao.readByName("wind of changes");

//        PersonDao dao = context.getBean(PersonDao.class);
//
//        System.out.println("All count " + dao.count());
//
//        dao.insert(new Person(2, "ivan"));
//
//        System.out.println("All count " + dao.count());
//
//        Person ivan = dao.getById(2);
//
//        System.out.println("Ivan id: " + ivan.getId() + " name: " + ivan.getName());
//
//        System.out.println(dao.getAll());

        Console.main(args);
    }
}
