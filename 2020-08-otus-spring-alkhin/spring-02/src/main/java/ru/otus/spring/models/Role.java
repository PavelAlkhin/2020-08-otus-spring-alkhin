package ru.otus.spring.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private long id;

    @Column(name = "role")
    private String role;

    public Role(String role) {
        this.role = role;
    }

    public Role() {

    }

    public Role(long id, String role) {
        this.id = id;
        this.role = role;
    }
}
