package ru.otus.spring.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.val;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

//@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "authors")
public class Author {

    @Id
    @Column
    private String id;

    @Column
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

    public static Author AuthorToDTO(Author author) {
        return new Author(author.getId(), author.getName(), author.getBooks());
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
