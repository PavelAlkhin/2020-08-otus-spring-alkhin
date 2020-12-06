package ru.otus.spring;

import lombok.val;
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

@SpringBootApplication
public class Main {

    @Autowired
    private UserRepository userRep;

    @Autowired
    private RoleRepository roleRep;

    public static void main(String[] args) {

        SpringApplication.run(Main.class, args);

    }

    @PostConstruct
    public void fillUsers() {

        val roleUser = roleRep.save(new Role("ROLE_USER"));

        val userUser = new User("user");
        userUser.setPassword("111");
        userUser.setActive(true);
        userUser.setName("userovich");
        userUser.setEmail("aaa@qqq");
        userUser.setLastName("userov");
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        userUser.setPassword(bCryptPasswordEncoder.encode(userUser.getPassword()));

        userUser.setRoles(Collections.singleton(roleUser));
        userRep.save(userUser);


        val userAdmin = new User("admin");
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
