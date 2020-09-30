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
    private List<Author> authors;

    @ManyToMany(targetEntity = Genre.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    @JoinTable(name = "books_genres", joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    @Fetch(FetchMode.SUBSELECT)
    private List<Genre> genres;

    @Override
    public String toString() {

        String descriptionBook = "Book{" + "id=" + id +
                "; title='" + title + "';" + " desc:'" + description + "'";
        String strAuthors = "; authors=";
        for (int i = 0; i<authors.size();i++) {
            strAuthors += " '" + authors.get(i).getName() + "'";
        }

        String strGenres = "; genres=";
        for (int i=0; i<genres.size();i++) {
            strGenres += " '" + genres.get(i).getName() + "'";
        }

        descriptionBook += strAuthors + strGenres + "}";

        return descriptionBook;
    }
}
