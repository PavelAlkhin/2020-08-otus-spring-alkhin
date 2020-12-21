package ru.otus.spring.service;

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
}
