package ru.otus.spring.models.mongo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "genres")
public class GenreMongo {
    @Id
    private String id;

    private String name;

    public GenreMongo(String name) {
        this.name = name;
    }
}
