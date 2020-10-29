package ru.otus.spring.rest;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.repositories.BookRepository;
import ru.otus.spring.rest.dto.BookDto;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
public class BookControllerRest {

    private final BookRepository repBook;

    @GetMapping("/api/books")
    public List<BookDto> getAllBooks() {
        return repBook.findAll().stream().map(BookDto::toDto)
                .collect(Collectors.toList());
    }
}
