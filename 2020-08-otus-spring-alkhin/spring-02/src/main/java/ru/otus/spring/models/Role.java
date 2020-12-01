package ru.otus.spring.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "roles")
public class Role {

    @Id
    private String id;
    private String role;

    public Role(String role) {
        this.role = role;
    }

}
