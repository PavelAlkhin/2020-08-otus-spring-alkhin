package ru.otus.spring.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.val;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @ManyToMany(targetEntity = Book.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "books_authors", joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    @Fetch(FetchMode.SUBSELECT)
    private List<Book> books;

    public Author(long id, String name) {
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
