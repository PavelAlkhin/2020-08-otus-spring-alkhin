package ru.otus.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.service.BookService;

import java.io.IOException;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<String> getBooks() throws IOException, InterruptedException {
        return ResponseEntity.ok(bookService.getBooks());
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> getBook(@PathVariable("id") long id) {
        return ResponseEntity.ok(bookService.getBook(id));
    }

}
