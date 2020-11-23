package ru.otus.spring.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.models.Book;
import ru.otus.spring.repositories.AuthorRepositoryWebFlux;
import ru.otus.spring.repositories.BookRepositoryWebFlux;
import ru.otus.spring.repositories.GenreRepositoryWebFlux;
import ru.otus.spring.rest.dto.BookAuthorsGenresDto;
import ru.otus.spring.rest.dto.FormBookForSaveDto;
import ru.otus.spring.rest.dto.FormBookForSaveNewBookDto;

import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping(value="/api")
public class BookControllerRest {

    private final BookRepositoryWebFlux repBook;
    private final AuthorRepositoryWebFlux repAuthor;
    private final GenreRepositoryWebFlux repGenre;

    @GetMapping("/books")
    public Flux<Book> getAllBooks() {
        return repBook.findAll();
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.GET)
    public Mono<ResponseEntity<BookAuthorsGenresDto>> editPage(@PathVariable("id") String id) {
        System.out.println("чтение по  "+id);
        return repBook.findById(id)
                .zipWith(repAuthor.findAll().collect(Collectors.toList()))
                .zipWith(repGenre.findAll().collect(Collectors.toList()))
                .map((b) -> (ResponseEntity.ok(new BookAuthorsGenresDto(b.getT1().getT1(), b.getT1().getT2(), b.getT2()))))
                ;
    }

    @ExceptionHandler
    @RequestMapping(value = "/save", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Mono<Mono<Mono<Mono<Book>>>> saveBook(@RequestBody FormBookForSaveDto bookForSaveDto) {

        return repBook.findById(bookForSaveDto.getId())
                .map(book1 -> (repAuthor.findAllByIdIn(bookForSaveDto.getAuthornames()).collect(Collectors.toList()))
                    .map(auth -> (repGenre.findAllByIdIn(bookForSaveDto.getGenrernames()).collect(Collectors.toList()))
                        .map(genr -> {
                                Book bookForSave = new Book(bookForSaveDto.getTitle(), bookForSaveDto.getDescription(), auth, genr);
                                bookForSave.setId(book1.getId());
                                bookForSave.setComments(book1.getComments());
                                bookForSave.addComment(bookForSaveDto.getNewcomment());
                                return repBook.save(bookForSave);
                            }
                        )
                    )
                );
    }

    @ExceptionHandler
    @RequestMapping(value = "/books/delete/{id}",  produces = "application/json", method = RequestMethod.DELETE)
    public Mono<Void> deleteBook(@PathVariable(value = "id") String id){
        System.out.println("удаление " + id);
        return repBook.deleteById(id);
    }

    @RequestMapping(value = "/authorsgenres", method = RequestMethod.GET)
    public Mono<ResponseEntity<BookAuthorsGenresDto>> getNewBook(FormBookForSaveDto bookForSaveDto){

        System.out.println("чтение для формирование новой книги");

        return repAuthor.findAll().collect(Collectors.toList())
                .zipWith(repGenre.findAll().collect(Collectors.toList()))
                .map((b) -> ResponseEntity.ok(new BookAuthorsGenresDto(null, b.getT1(), b.getT2())))
                ;
    }

    @ExceptionHandler
    @RequestMapping(value = "/savenew", method = RequestMethod.PUT)
    public @ResponseBody Mono<Mono<Book>> saveNewBook(@RequestBody FormBookForSaveNewBookDto book){
        return repAuthor.findAllByIdIn(book.getAuthornames()).collect(Collectors.toList())
                .zipWith(repGenre.findAllByIdIn(book.getGenrernames()).collect(Collectors.toList()))
                .map(b -> {
                    Book newBook = new Book(book.getTitle(), book.getDescription(), b.getT1(), b.getT2());
                    newBook.addComment(book.getNewcomment());
                    return repBook.save(newBook);
                })
                ;
    }

}
