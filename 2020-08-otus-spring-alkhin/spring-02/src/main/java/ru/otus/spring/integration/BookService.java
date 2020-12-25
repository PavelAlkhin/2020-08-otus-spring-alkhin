package ru.otus.spring.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.models.Book;
import ru.otus.spring.repositories.BookRepository;

@Service
public class BookService {


    @Autowired
    BookRepository bookRepository;

    @Transactional
    public Book saveBook(Book book) throws Exception {
        System.out.println("Сохраняем книгу " + book.getTitle());
        Book savedBook = bookRepository.save(book);

        return savedBook;
    }

    @Transactional
    public Book getBookById(long id) throws Exception {
        System.out.println( "получаем книгу по Id " );
        Book book = bookRepository.findById(id).orElseThrow();

        return book;
    }

}
