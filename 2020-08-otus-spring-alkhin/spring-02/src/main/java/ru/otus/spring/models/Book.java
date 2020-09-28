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
@Table(name = "books")
//@NamedEntityGraph(name="authors-entity-graph", attributeNodes = {@NamedAttributeNode("author")})
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToMany(targetEntity = Author.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "books_authors", joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    @Fetch(FetchMode.SUBSELECT)
    private List<Author> author;

    @ManyToMany(targetEntity = Genre.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    @JoinTable(name = "books_genres", joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    @Fetch(FetchMode.SUBSELECT)
    private List<Genre> genre;

    @Override
    public String toString() {

        val listAuthors = author.iterator();
        val listGenres = genre.iterator();

        String descriptionBook = "Book{" + "id=" + id +
                "; title='" + title + "'" + " desc:'" + description + "';";
        String authors = "; authors=";
        while (listAuthors.hasNext()) {
            val iterAuthor = listAuthors.next();
            authors += " '" + iterAuthor.getName() + "'";
        }
        String genres = "; genres=";
        while (listGenres.hasNext()) {
            val iterGenre = listGenres.next();
            genres += " '" + iterGenre.getName() + "'";
        }

        descriptionBook += authors + genres + "}";

        return descriptionBook;

    }
}
