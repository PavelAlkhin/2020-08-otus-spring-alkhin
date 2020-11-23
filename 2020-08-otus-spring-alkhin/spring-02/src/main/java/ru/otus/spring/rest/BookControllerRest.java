package ru.otus.spring.rest;

import lombok.AllArgsConstructor;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.models.Author;
import ru.otus.spring.models.Book;
import ru.otus.spring.models.Genre;
import ru.otus.spring.repositories.*;
import ru.otus.spring.rest.dto.BookAuthorsGenresDto;
import ru.otus.spring.rest.dto.FormBookForSaveDto;
import ru.otus.spring.rest.dto.FormBookForSaveNewBookDto;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value="/api")
public class BookControllerRest {

    private final BookRepository repBook;
    private final AuthorRepository repAuthor;
    private final GenreRepository repGenre;

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return repBook.findAll();
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.GET)
    public ResponseEntity<BookAuthorsGenresDto> editPage(@PathVariable("id") String id) {
        System.out.println("чтение по  "+id);

        List<Author> authorList = new ArrayList<>();
        repAuthor.findAll().forEach(a -> authorList.add(a));

        List<Genre> genreList = getGenreList();
        repGenre.findAll().iterator().forEachRemaining(g -> genreList.add(g));

        return ResponseEntity.ok(new BookAuthorsGenresDto(
                    repBook.findById(id).orElseThrow(),
                    authorList,
                    genreList
                )
        );
    }

    @NotNull
    private ArrayList<Genre> getGenreList() {
        return new ArrayList<>();
    }

    @ExceptionHandler
    @RequestMapping(value = "/save", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Book saveBook(@RequestBody FormBookForSaveDto bookForSaveDto) {

        val book = repBook.findById(bookForSaveDto.getId()).orElseThrow();
        val authorList = repAuthor.findAllByIdIn(bookForSaveDto.getAuthornames());
        val genreList = repGenre.findAllByIdIn(bookForSaveDto.getGenrernames());

        Book bookForSave = new Book(bookForSaveDto.getTitle(), bookForSaveDto.getDescription(), authorList, genreList);
        bookForSave.setId(book.getId());
        bookForSave.setComments(book.getComments());
        bookForSave.addComment(bookForSaveDto.getNewcomment());

        return repBook.save(bookForSave);
    }

    @ExceptionHandler
    @RequestMapping(value = "/books/delete/{id}",  produces = "application/json", method = RequestMethod.DELETE)
    public void deleteBook(@PathVariable(value = "id") String id){
        System.out.println("удаление " + id);
        repBook.deleteById(id);
    }

    @RequestMapping(value = "/authorsgenres", method = RequestMethod.GET)
    public ResponseEntity<BookAuthorsGenresDto> getNewBook(FormBookForSaveDto bookForSaveDto){

        System.out.println("чтение для формирование новой книги");

        List<Author> authorList = new ArrayList<>();
        repAuthor.findAll().forEach(a -> authorList.add(a));

        List<Genre> genreList = getGenreList();
        repGenre.findAll().iterator().forEachRemaining(g -> genreList.add(g));

       return ResponseEntity.ok(new BookAuthorsGenresDto(null, authorList, genreList));
    }

    @ExceptionHandler
    @RequestMapping(value = "/savenew", method = RequestMethod.PUT)
    public @ResponseBody Book saveNewBook(@RequestBody FormBookForSaveNewBookDto book){

        val authorList = repAuthor.findAllByIdIn(book.getAuthornames());
        val genreList = repGenre.findAllByIdIn(book.getGenrernames());

        Book newBook = new Book(book.getTitle(), book.getDescription(), authorList, genreList);
        newBook.addComment(book.getNewcomment());
        return repBook.save(newBook);
    }
}
