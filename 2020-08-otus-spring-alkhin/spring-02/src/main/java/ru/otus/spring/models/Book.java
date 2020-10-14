package ru.otus.spring.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.val;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "books")
public class Book {

    @Id
    private String id;

    private String title;

    private String description;

    private List<Author> authors;

    private List<Genre> genres;

    public Book(String title, String description, List<Author> authors, List<Genre> genres) {
        this.title = title;
        this.description = description;
        this.authors = authors;
        this.genres = genres;
    }

    @Override
    public String toString() {

        String descriptionBook = "Book{" + "id=" + id +
                "; title='" + title + "';" + " desc:'" + description + "'";
        String strAuthors = "; authors=";
        for(Author author : authors){
            strAuthors += " '" + author.getName() + "'";
        }

        String strGenres = "; genres=";
        for (Genre genre : genres) {
            strGenres += " '" + genre.getName() + "'";
        }

        descriptionBook += strAuthors + strGenres + "}";

        return descriptionBook;
    }
}
