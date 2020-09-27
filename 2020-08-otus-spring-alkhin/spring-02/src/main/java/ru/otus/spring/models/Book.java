package ru.otus.spring.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.val;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
//@NamedEntityGraph(name="authors-entuty-graph", attributeNodes = {@NamedAttributeNode("author")})
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title", nullable = false, unique = true)
    private String title;


    @ManyToMany(targetEntity = Author.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "books_authors", joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
//    @Fetch(FetchMode.SUBSELECT)
    @BatchSize(size = 5)
    private List<Author> author;

    // Указывает на связь между таблицами "один ко многим"
    @ManyToMany(targetEntity = Genre.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "books_genres", joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    //    @Column(name = "genre_id", nullable = false, unique = true)
    private List<Genre> genre;

    @Override
    public String toString() {

        val listAuthors = author.iterator();
        val listGenres = genre.iterator();

        String descriptionBook = "Book{" +
                "id=" + id +
                ", title='" + title + '\'';
        String authors = ", authors='";
        while (listAuthors.hasNext()) {
            val iterAuthor = listAuthors.next();
            authors += "; " + iterAuthor.getName() + '\'';
        }
        String genres = ", genres='";
        while (listGenres.hasNext()) {
            val iterGenre = listGenres.next();
            genres += "; " + iterGenre.getName() + '\'';
        }

        descriptionBook += authors + genres + '}';

        return descriptionBook;

    }
}