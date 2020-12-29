package ru.otus.spring.rest;

import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.acls.model.MutableAclService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.models.Author;
import ru.otus.spring.models.Book;
import ru.otus.spring.models.Genre;
import ru.otus.spring.models.User;
import ru.otus.spring.repositories.AuthorRepository;
import ru.otus.spring.repositories.BookRepository;
import ru.otus.spring.repositories.GenreRepository;
import ru.otus.spring.repositories.UserRepository;
import ru.otus.spring.rest.dto.BookAuthorsGenresDto;
import ru.otus.spring.rest.dto.FormBookForSaveDto;
import ru.otus.spring.rest.dto.FormBookForSaveNewBookDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@RestController
@RequestMapping(value="/api")
@AllArgsConstructor
public class BookControllerRest {

    private UserRepository repUser;

    private BookRepository repBook;

    private AuthorRepository repAuthor;

    private GenreRepository repGenre;

//    protected MutableAclService mutableAclService;

    private ArrayList<Genre> getGenreEmptyList() {
        return new ArrayList<>();
    }

    private static final Logger LOGGER = LogManager.getLogger(BookControllerRest.class.getName());

    @Transactional
    @GetMapping("/books")
    public List<Book> getAllBooks() throws InterruptedException {
        LOGGER.info(" выборка всех книг ");
        return repBook.findAll();
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.GET)
    public ResponseEntity<BookAuthorsGenresDto> editPage(@PathVariable("id") long id) throws Exception {
        System.out.println("чтение по  "+id);
        LOGGER.info("чтение по  "+id);

        List<Author> authorList = new ArrayList<>();
        repAuthor.findAll().forEach(a -> authorList.add(a));

        List<Genre> genreList = getGenreEmptyList();
        repGenre.findAll().iterator().forEachRemaining(g -> genreList.add(g));

        Optional<Book> optionalBook= repBook.findById(id);

        Book b = optionalBook.orElseThrow(() -> new Exception("Book not found - " + id));

        return ResponseEntity.ok(new BookAuthorsGenresDto(
                    b,
                    authorList,
                    genreList
                )
        );
    }

    @RequestMapping(value = "/save", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Book saveBook(@RequestBody FormBookForSaveDto bookForSaveDto) throws Throwable {

        Book book = repBook.findById(bookForSaveDto.getId()).orElseThrow(() -> new Exception("Book not found - " + bookForSaveDto.getId()));
        List<Author> authorList = repAuthor.findAllByIdIn(bookForSaveDto.getAuthornames());
        List<Genre> genreList = repGenre.findAllByIdIn(bookForSaveDto.getGenrernames());

        Book bookForSave = new Book(bookForSaveDto.getTitle(), bookForSaveDto.getDescription(), authorList, genreList);
        bookForSave.setId(book.getId());
        bookForSave.setComments(book.getComments());
        bookForSave.addComment(bookForSaveDto.getNewcomment());

        LOGGER.info("сохранение книгу по  "+book.getId());

        return repBook.save(bookForSave);

    }

    @RequestMapping(value = "/books/delete/{id}",  produces = "application/json", method = RequestMethod.DELETE)
    public void deleteBook(@PathVariable(value = "id") long id){
        System.out.println("удаление " + id);
        LOGGER.info("удаление книги "+id);

        repBook.deleteById(id);
    }

    @RequestMapping(value = "/authorsgenres", method = RequestMethod.GET)
    public ResponseEntity<BookAuthorsGenresDto> getNewBook(FormBookForSaveDto bookForSaveDto){

        System.out.println("чтение для формирование новой книги");

        List<Author> authorList = new ArrayList<>();
        repAuthor.findAll().forEach(a -> authorList.add(a));

        List<Genre> genreList = getGenreEmptyList();
        repGenre.findAll().iterator().forEachRemaining(g -> genreList.add(g));

       return ResponseEntity.ok(new BookAuthorsGenresDto(null, authorList, genreList));
    }

    @Transactional
    @RequestMapping(value = "/savenew", method = RequestMethod.PUT)
    public @ResponseBody Book saveNewBook(@RequestBody FormBookForSaveNewBookDto book){

        val authorList = repAuthor.findAllByIdIn(book.getAuthornames());
        val genreList = repGenre.findAllByIdIn(book.getGenrernames());

        Book newBook = new Book(book.getTitle(), book.getDescription(), authorList, genreList);

        newBook.addComment(book.getNewcomment());
        LOGGER.info("сохранение новой книги "+newBook.getId());
        return repBook.save(newBook);

    }

    @RequestMapping(value="/users", method = RequestMethod.GET)
    public List<User> adminPage(){
        val users = new ArrayList<User>();
        repUser.findAll().forEach(g->users.add(g));
        return users;
    }
}
