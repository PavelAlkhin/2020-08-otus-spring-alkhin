package ru.otus.spring.models.mongo;

import lombok.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "books")
public class BookMongo {

    @Id
    private String id;

    private String title;

    private String description;

    private List<AuthorMongo> authors;

    private List<GenreMongo> genres;

    @Getter
    private List<CommentMongo> comments;

    public BookMongo(String title, String description, List<AuthorMongo> authors, List<GenreMongo> genres) {
        this.title = title;
        this.description = description;
        this.authors = authors;
        this.genres = genres;
    }

    public void setComment(String comment){
        if(comments == null) {
            comments = new ArrayList<>();
        }
        comments.add(new CommentMongo(comment));

    }

    public void setComments(List<CommentMongo> comments) {
        this.comments = comments;
    }
}
