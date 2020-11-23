package ru.otus.spring.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User {

    @Id
    private String id;

    private Boolean active;

    private String userName;

    private String email;

    private String password;

    private String name;

    private String lastName;

    private Set<Role> roles;

    public User(String username) {
        this.userName = username;
    }

    public User(String username, String password) {
        this.userName = username;
        this.password = password;
    }


}
