package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.val;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.models.Author;
import ru.otus.spring.service.BookService;
import ru.otus.spring.service.ScannerService;

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

        System.out.println("Enter Author name ");
        String authorName = scannerService.read();

        System.out.println("Enter Genre type ");
        String genreType = scannerService.read();

        System.out.println("Enter description ");
        String description = scannerService.read();

        bookService.saveBook(title, authorName, genreType, description);

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

    @ShellMethod(value = "print books by Author name", key = {"print authors books", "print by author"})
    public void printBooksByAuthorId(String name) {
        val books = bookService.getBooksByAuthorName(name);
        bookService.printBooks(books);
    }

}
