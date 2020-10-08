package ru.otus.spring.service;

import lombok.AllArgsConstructor;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.models.Author;
import ru.otus.spring.models.Book;
import ru.otus.spring.models.Genre;
import ru.otus.spring.repositories.AuthorRepository;
import ru.otus.spring.repositories.BookRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Service
public class BookServiceImpl implements BookService{

    private final AuthorRepository authorRepositoryJpa;
    private final BookRepository bookRepositoryDataJpa;
    private final Logger LOG = LoggerFactory.getLogger(BookServiceImpl.class);

    @Override
    @Transactional
    public int countBooks() {
        return (int) bookRepositoryDataJpa.count();
    }

    @Override
    @Transactional
    public void saveBook(String title, String author, String genre, String description) {
        val authors = Collections.singletonList(new Author(0, author));
        val genres = Collections.singletonList(new Genre(0, genre));
        bookRepositoryDataJpa.save(new Book(0, title, description, authors, genres));
        LOG.info("save new book " + title);
    }

    @Override
    public void printBooks(Iterable<Book> books) {
        for(Book book : books){
            System.out.println(book.toString());
        }

    }

    @Override
    @Transactional
    public void printAllBooks() {
        val books = bookRepositoryDataJpa.findAll();
        printBooks(books);
    }

    @Override
    @Transactional
    public List<Book> findByTite(String title) {
        return bookRepositoryDataJpa.findByTitle(title);
    }


    @Override
    @Transactional
    public List<Book> getBooksByAuthorName(String name) {

        LOG.info("get book by author name " + name);

        val author = authorRepositoryJpa.findByName(name);

        try {
            return author.getBooks();
        }catch (NullPointerException npe){
            System.out.println("no author with name " + name + "; " + npe);
        }

        return new ArrayList<>();
    }

    @Override
    @Transactional
    public void printBooksByAuthorName(String name) {
        val books = getBooksByAuthorName(name);
        for(Book book: books){
            System.out.println(book.toString());
        }
    }
}