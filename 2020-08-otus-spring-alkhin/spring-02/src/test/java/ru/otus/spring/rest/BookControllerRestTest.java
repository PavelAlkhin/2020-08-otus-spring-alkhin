package ru.otus.spring.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import ru.otus.spring.models.Book;

@SpringBootTest
class BookControllerRestTest {

//    @Autowired
//    private RouterFunction route;

    @Autowired
    private WebTestClient webClient;

    @Test
    void getAllBooks() {
//        WebTestClient client = WebTestClient
//                .bindToRouterFunction(route)
//                .build();
//        client.get()
//                .uri("/api/books")
//                .exchange()
//                .expectStatus()
//                .isOk();

        webClient.get().uri("/api/books/{id}", "Test")
                .header(HttpHeaders.ACCEPT, "application/json")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Book.class);
    }

    @Test
    void editPage() {
    }

    @Test
    void saveBook() {
    }

    @Test
    void deleteBook() {
    }

    @Test
    void getNewBook() {
    }

    @Test
    void saveNewBook() {
    }
}