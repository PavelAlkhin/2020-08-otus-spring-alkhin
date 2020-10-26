package ru.otus.spring.rest;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
@Controller
public class BookController {

    private final BookRepository repBook;
    private final AuthorRepository repAuthor;
    private final GenreRepository repGenre;

    @GetMapping("/")
    public String listPage(Model model) {
        List<Book> books = (List<Book>) repBook.findAll();
        model.addAttribute("books", books);
        return "list";
    }

    @GetMapping("/edit")
    public String editPage(@RequestParam("id") String id, Model model) {

        List<Author> authorList = (List<Author>) repAuthor.findAll();
        List<Genre> genreList = (List<Genre>) repGenre.findAll();

        Book book = repBook.findById(id).orElseThrow();

        model.addAttribute("book", book);
        model.addAttribute("authorList", authorList);
        model.addAttribute("genreList", genreList);

        return "edit";
    }

    @PostMapping("/edit")
    public String saveBook(
            Book book,
            @RequestParam(value = "save", required = false) String save,
            @RequestParam(value = "delete", required = false) String delete,
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "authornames", required = false) String authornames,
            @RequestParam(value = "genrernames", required = false) String genrernames,
            @RequestParam(value = "comment", required = false) String comment,
            Model model
                            ) {
        if(delete != null){
            repBook.delete(book);
            return "redirect:/";
        }

        List<Author> authorList = new ArrayList<>();
        List<Genre> genreList = new ArrayList<>();
        String[] genres = genrernames.split(",");
        for(String genreId : genres){
            genreList.add(repGenre.findById(genreId).orElseThrow());
        }

        String[] authors = authornames.split(",");
        for(String authorId : authors){
            authorList.add(repAuthor.findById(authorId).orElseThrow());
        }

        Book bookForSave = new Book(title, description, authorList, genreList);
        bookForSave.setId(id);
        bookForSave.setComment(comment);
        Book saved = repBook.save(bookForSave);
        model.addAttribute(saved);
        return "redirect:edit?id="+saved.getId();
    }

    @GetMapping("/newbook")
    public String newbook(Model model) {

        List<Author> authorList = (List<Author>) repAuthor.findAll();
        List<Genre> genreList = (List<Genre>) repGenre.findAll();

        model.addAttribute("authorList", authorList);
        model.addAttribute("genreList", genreList);

        return "newbook";
    }

    @PostMapping("/newbook")
    public String addNewbook(
                            @RequestParam(value = "title", required = false) String title,
                            @RequestParam(value = "description", required = false) String description,
                            @RequestParam(value = "authornames", required = false) String authornames,
                            @RequestParam(value = "genrernames", required = false) String genrernames,
                            @RequestParam(value = "comment", required = false) String comment,
                            Model model) {

        List<Author> authorList = new ArrayList<>();
        List<Genre> genreList = new ArrayList<>();
        String[] genres = genrernames.split(",");
        for(String genreId : genres){
            genreList.add(repGenre.findById(genreId).orElseThrow());
        }

        String[] authors = authornames.split(",");
        for(String authorId : authors){
            authorList.add(repAuthor.findById(authorId).orElseThrow());
        }
        Book book = new Book(title, description, authorList, genreList);
        book.setComment(comment);
        Book saved = repBook.save(book);

        return "redirect:/";
    }
}
