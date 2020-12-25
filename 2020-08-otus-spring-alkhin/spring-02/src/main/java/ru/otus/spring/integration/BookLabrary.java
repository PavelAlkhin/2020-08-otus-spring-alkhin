package ru.otus.spring.integration;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.spring.models.Book;

@MessagingGateway
public interface BookLabrary {

    @Gateway(requestChannel = "saveBookFlow.input", replyChannel = "outputChannel")
    Book saveBook(Book book);

    @Gateway(requestChannel = "getBookById.input", replyChannel = "outputChannel")
    Book getBookById(long id);

}
