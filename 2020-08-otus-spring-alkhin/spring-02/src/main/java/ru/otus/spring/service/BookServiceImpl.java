package ru.otus.spring.service;

import lombok.AllArgsConstructor;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.models.Author;
import ru.otus.spring.models.Book;
import ru.otus.spring.models.Comment;
import ru.otus.spring.models.Genre;
import ru.otus.spring.repositories.AuthorRepository;
import ru.otus.spring.repositories.BookRepository;
import ru.otus.spring.repositories.GenreRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Service
public class BookServiceImpl implements BookService{

    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final Logger LOG = LoggerFactory.getLogger(BookServiceImpl.class);
    private final ScannerService scanner;

    @Override
    @Transactional
    public int countBooks() {
        return (int) bookRepository.count();
    }

    @Override
    @Transactional
    public void saveBook(String title, List<String> authors, List<String> genres, String description) {

        List<Author> authList = new ArrayList<>();
        for(String author : authors){
            authList.add(new Author(author));
        }

        List<Genre> genreList = new ArrayList<>();
        for(String genre : genres){
            genreList.add(new Genre(genre));
        }

        authorRepository.saveAll(authList);
        genreRepository.saveAll(genreList);
        bookRepository.save(new Book(title, description, authList, genreList));

        LOG.info("save new book " + title);
    }

    @Override
    @Transactional
    public void printBooks(Iterable<Book> books) {
        for(Book book : books){

            val title = book.getTitle();
            val description = book.getDescription();
            val authors = book.getAuthors();
            val id = book.getId();
            val genres = book.getGenres();

            String descriptionBook = "Book{" + "id=" + id +
                    "; title='" + title + "';" + " desc:'" + description + "'";
            String strAuthors = "; authors=";
            for(Author author : authors){
                strAuthors += " '" + author.getName() + "'";
            }

            String strGenres = "; genres=";
            for (Genre genre : genres) {
                strGenres += " '" + genre.getName() + "'";
            }

            descriptionBook += strAuthors + strGenres + "}";
            System.out.println(book.toString());
        }
    }

    @Override
    @Transactional
    public void printAllBooks() {
        val books = bookRepository.findAll();
        printBooks(books);
    }

    @Override
    @Transactional
    public List<Book> findByTite(String title) {
        return bookRepository.findByTitle(title);
    }


    @Override
    @Transactional
    public List<Book> getBooksByAuthorName(String name) {

        LOG.info("get book by author name " + name);

        val author = authorRepository.findByName(name);

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
        Author author = authorRepository.findByName(name);
        try {
            System.out.println(author.getName());
        }catch (NullPointerException npe){
            System.out.println("No Author with name " + name);
            return;
        }
        val books = bookRepository.findByAuthorsContains(author);
        if(books.size() == 0){
            System.out.println("No books");
        }
        for(Book book: books){
            System.out.println(book.toString());
        }
    }

    @Override
    public void readListFromScanner(List<String> answerList, String enterType) {
        String answerString = readFromScannerString(enterType);

        if(answerString.equals("f")||answerString.equals("")){
            return;
        }

        if(!answerString.equals("f")){
            if(!answerList.contains(answerString)) {
                answerList.add(answerString);
            }
        }

        readListFromScanner(answerList, enterType);
    }

    @Override
    @Transactional
    public int countAuthors() {
        return (int) authorRepository.count();
    }

    @Override
    @Transactional
    public void printAllAuthors() {
        val authors = authorRepository.findAll();
        printAuthors(authors);
    }

    @Override
    public void printCommentsByTitle(String title) {
        try{
            val books = bookRepository.findByTitle(title);
            for (Book book : books){
                for(Comment com : book.getComments()){
                    System.out.println(com.getText());
                }
            }
        }catch (NullPointerException npe){
            System.out.println("" + npe + "not found book with title: " + title);
        }
    }

    private void printAuthors(Iterable<Author> authors) {
        for(Author author: authors){
            System.out.println(author.toString());
        }
    }

    private String readFromScannerString(String enterType){
        System.out.println("Enter " + enterType + " name , finish - f or empty");
        return scanner.read();
    }
}