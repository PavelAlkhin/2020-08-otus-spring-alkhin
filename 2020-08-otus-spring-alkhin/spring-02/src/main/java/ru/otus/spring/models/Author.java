package ru.otus.spring.models;

import lombok.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "authors")
public class Author {

    @Id
    private String id;

    private String name;

    private List<Book> books;

    public Author(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Author(String name, List<Book> books) {
        this.name = name;
        this.books = books;
    }

    public Author(String name) {
        this.name = name;
    }

    public String printBooks(){
        String strBooks = "Books=";
        for (int i = 0; books.size()>i;i++){
            val book = books.get(i);
            strBooks += "'" + book.getTitle() + "';";
        }
        return strBooks;
    }

}
