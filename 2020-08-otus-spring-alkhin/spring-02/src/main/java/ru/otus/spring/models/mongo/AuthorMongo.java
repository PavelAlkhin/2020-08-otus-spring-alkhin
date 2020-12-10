package ru.otus.spring.models.mongo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "authors")
public class AuthorMongo {

    @Id
    private String id;

    private String name;

    public AuthorMongo(String name, List<BookMongo> books) {
        this.name = name;
    }

    public AuthorMongo(String name) {
        this.name = name;
    }

}
