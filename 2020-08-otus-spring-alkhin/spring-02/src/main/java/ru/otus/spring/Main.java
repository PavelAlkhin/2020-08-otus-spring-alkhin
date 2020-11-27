package ru.otus.spring;

import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.otus.spring.models.Role;
import ru.otus.spring.models.User;
import ru.otus.spring.repositories.RoleRepository;
import ru.otus.spring.repositories.UserRepository;
import ru.otus.spring.service.InitialFillings;

import java.util.Collections;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@SpringBootApplication
@AllArgsConstructor
@EnableJpaRepositories
@Configuration
public class Main {

    @Autowired
    private static InitialFillings initialFillings;

    @Autowired
    private static UserRepository userRep;

    @Autowired
    private static RoleRepository roleRep;

    public static void main(String[] args) {

        SpringApplication.run(Main.class, args);

        fillUsers();

    }

    @Bean
    public static void fillUsers() {

        val userUser = new User("user");
        userUser.setPassword("111");
        userUser.setActive(true);
        userUser.setName("userovich");
        userUser.setEmail("aaa@qqq");
        userUser.setLastName("userov");

        User userFromDB = userRep.findByUserName(userUser.getUserName());

        if (userFromDB != null) {
            return;
        }

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        Role roleUser = roleRep.findByRole("ROLE_USER");
        if(roleUser == null){
            roleUser = roleRep.save(new Role("ROLE_USER"));
        }

        userUser.setRoles(Collections.singleton(roleUser));
        userUser.setPassword(bCryptPasswordEncoder.encode(userUser.getPassword()));
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
        userAdmin.setActive(true);
        userAdmin.setName("adminych");
        userAdmin.setEmail("adm@qqq");
        userAdmin.setLastName("adminov");

        userAdmin.setRoles(Collections.singleton(roleAdmin));
        userAdmin.setPassword(bCryptPasswordEncoder.encode(userAdmin.getPassword()));
        userRep.save(userAdmin);
    }


}
