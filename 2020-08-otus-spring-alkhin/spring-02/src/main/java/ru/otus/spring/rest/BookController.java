package ru.otus.spring.rest;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.spring.repositories.AuthorRepositoryWebFlux;
import ru.otus.spring.repositories.BookRepositoryWebFlux;
import ru.otus.spring.repositories.GenreRepositoryWebFlux;

@AllArgsConstructor
@Controller
public class BookController {

    private final BookRepositoryWebFlux repBook;
    private final AuthorRepositoryWebFlux repAuthor;
    private final GenreRepositoryWebFlux repGenre;

    @GetMapping("/")
    public String listPage(Model model) { return "list"; }

    @GetMapping("/books")
    public String editPage(@RequestParam(value = "id", required = false) String id, Model model) { return "edit"; }

    @GetMapping("/newbook")
    public String newbook(Model model) { return "newbook"; }
}
