package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.service.BookService;
import ru.otus.spring.service.ScannerService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@ShellComponent
public class ApplicationEventsCommands {

    private final ScannerService scannerService;
    private final BookService bookService;

    @SneakyThrows
    @ShellMethod(value = "save new book", key = {"s book", "save book"})
    public String saveNewBook() {

        System.out.println("Enter Title ");
        String title = scannerService.read();

        List<String> authorNames = new ArrayList<>();
        bookService.readListFromScanner(authorNames, "Authors");

        List<String> genresList = new ArrayList<>();
        bookService.readListFromScanner(genresList, "Genres");

        System.out.println("Enter description ");
        String description = scannerService.read();

        bookService.saveBook(title, authorNames, genresList, description);

        return "To save new book - type `save new book` or `save book`";
    }

    @ShellMethod(value = "Count books", key = {"count", "c"})
    public int countBooks() {
        return bookService.countBooks();
    }

    @ShellMethod(value = "print all books", key = {"print books", "print all"})
    public void printAllBooks() {
        bookService.printAllBooks();
    }

    @ShellMethod(value = "print books by Author name", key = {"print authors books", "print by author", "p a"})
    public void printBooksByAuthorId(String name) {
        bookService.printBooksByAuthorName(name);
    }

    @ShellMethod(value = "print all book`s comments", key = {"print com book", "print comment by title", "p c"})
    public void printCommentsByTitle(String title) {
        bookService.printCommentsByTitle(title);
    }

}
