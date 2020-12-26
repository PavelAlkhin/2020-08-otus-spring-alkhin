package ru.otus.spring.models;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @Column(name = "description")
    private String description;

    @Setter @Getter
    @ManyToMany(targetEntity = Author.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "books_authors", joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    @Fetch(FetchMode.SUBSELECT)
    private List<Author> authors;

    @Setter @Getter
    @ManyToMany(targetEntity = Genre.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    @JoinTable(name = "books_genres", joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    @Fetch(FetchMode.SUBSELECT)
    private List<Genre> genres;

    @Getter
    @ManyToMany(targetEntity = BookComment.class, fetch = FetchType.LAZY , cascade = CascadeType.ALL )
    @JoinTable(name = "books_comments", joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "comment_id"))
    private List<BookComment> comments;

    public Book(String title, String description, List<Author> authors, List<Genre> genres) {
        this.title = title;
        this.description = description;
        this.authors = authors;
        this.genres = genres;
    }

    public Book(long id, String title, String description, List<Author> authors, List<Genre> genres, List<BookComment> comments) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.authors = authors;
        this.genres = genres;
        this.comments = comments;
    }

    public Book() {
    }

    public void addComment(String comment){
        if(comments == null) {
            comments = new ArrayList<>();
        }
        comments.add(new BookComment(comment));

    }

    public void setComments(List<BookComment> comments) {
        this.comments = comments;
    }
}
