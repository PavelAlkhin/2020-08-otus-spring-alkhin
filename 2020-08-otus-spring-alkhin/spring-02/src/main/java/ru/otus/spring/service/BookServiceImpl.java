package ru.otus.spring.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.sql.SQLException;
import java.util.List;

@AllArgsConstructor
@Service(value = "BookService")
public class BookServiceImpl implements BookService{

    private final BookDao bookDao;
    private final ScannerService scannerService;

    @Override
    public void saveBook(String title, String author, String genre) throws SQLException {
        bookDao.save(new Book(title, new Author(author), new Genre(genre)));
    }

    @Override
    public int countBooks() {
        return bookDao.count();
    }

    @Override
    public void printAllBooks() {
        List<Book> bookList = bookDao.getAll();
        for (int i = 0; i < bookList.size(); i++) {
            System.out.println(""+(i+1)+". "+bookList.get(i).toString());
        }
    }
}
