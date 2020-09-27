//package ru.otus.spring.shell;
//
//import lombok.RequiredArgsConstructor;
//import lombok.SneakyThrows;
//import org.springframework.shell.standard.ShellComponent;
//import org.springframework.shell.standard.ShellMethod;
//
//@RequiredArgsConstructor
//@ShellComponent
//public class ApplicationEventsCommands {
//
//    private final ScannerService scannerService;
//    private final BookService bookService;
//
//    @SneakyThrows
//    @ShellMethod(value = "save new book", key = {"s book", "save book"})
//    public String saveNewBook() {
//
//        System.out.println("Enter Title ");
//        String title = scannerService.read();
//
//        System.out.println("Enter Author name ");
//        String authorName = scannerService.read();
//
//        System.out.println("Enter Genre type ");
//        String genreType = scannerService.read();
//
//        bookService.saveBook(title, authorName, genreType);
//
//        return "To save new book - type `save new book` or `save book`";
//    }
//
//    @ShellMethod(value = "Count books", key = {"count", "c"})
//    public int countBooks() {
//        return bookService.countBooks();
//    }
//
//}
