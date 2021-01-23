package ru.otus.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.otus.spring.models.Role;
import ru.otus.spring.models.User;
import ru.otus.spring.repositories.RoleRepository;
import ru.otus.spring.repositories.UserRepository;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

//@EnableCircuitBreaker
@SpringBootApplication
public class BooksApp {

    @Autowired
    private UserRepository userRep;

    @Autowired
    private RoleRepository roleRep;

    public static void main(String[] args) {

        SpringApplication.run(BooksApp.class, args);

    }

    @PostConstruct
    public void fillUsers() {

        Role roleUser = roleRep.save(new Role("ROLE_USER"));

        User userUser = new User("user");
        userUser.setPassword("111");
        userUser.setActive(true);
        userUser.setName("userovich");
        userUser.setEmail("aaa@qqq");
        userUser.setLastName("userov");
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        userUser.setPassword(bCryptPasswordEncoder.encode(userUser.getPassword()));

        userUser.setRoles(Collections.singleton(roleUser));
        userRep.save(userUser);

        User userAdmin = new User("admin");
        userAdmin.setPassword("111");
        User userFromDB2 = userRep.findByUserName(userAdmin.getUserName());

        if (userFromDB2 != null) {
            return;
        }

        Role roleAdmin = roleRep.findByRole("ROLE_ADMIN");
        if(roleAdmin == null){
            roleAdmin = roleRep.save(new Role("ROLE_ADMIN"));
        }

        Role roleEditor = roleRep.findByRole("ROLE_EDITOR");
        if(roleEditor == null){
            roleEditor = roleRep.save(new Role("ROLE_EDITOR"));
        }

        Set<Role> roleList = new HashSet<>();

        roleList.add(roleAdmin);
        roleList.add(roleEditor);

        userAdmin.setActive(true);
        userAdmin.setName("adminych");
        userAdmin.setEmail("adm@qqq");
        userAdmin.setLastName("adminov");

        userAdmin.setRoles(roleList);
        userAdmin.setPassword(bCryptPasswordEncoder.encode(userAdmin.getPassword()));
        userRep.save(userAdmin);
    }

}
