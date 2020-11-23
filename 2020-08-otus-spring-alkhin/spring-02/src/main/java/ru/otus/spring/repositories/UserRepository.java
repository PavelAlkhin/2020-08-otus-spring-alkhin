package ru.otus.spring.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.models.User;

public interface UserRepository extends CrudRepository<User, String> {

    User findByUserName(String userName);

    User findByEmail(String email);
}
