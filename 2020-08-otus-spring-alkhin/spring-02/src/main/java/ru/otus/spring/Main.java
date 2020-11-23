package ru.otus.spring;

import com.github.cloudyrock.spring.v5.EnableMongock;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableMongock
@EnableMongoRepositories
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@SpringBootApplication
@AllArgsConstructor
public class Main {

    public static void main(String[] args) {


        ApplicationContext ctx = SpringApplication.run(Main.class, args);

//        UserServiceImpl userService = ctx.getBean("UserServiceImpl", UserServiceImpl.class);


//        userService.saveUser(new User("user","pass"));

//        userUser.setPassword("pass");
////        userServiceImpl.saveUser(userUser);
////
//        val userAdmin = new User("admin");
//        userAdmin.setPassword("pass");
////        userServiceImpl.saveUser(userAdmin);
//
//        User userFromDB = userRep.findByUsername(userUser.getUsername());
//
//        if (userFromDB != null) {
//            return;
//        }
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//
//        userUser.setRoles(Collections.singleton(new Role("ROLE_USER")));
////        userUser.setPassword(bCryptPasswordEncoder.encode(userUser.getPassword()));
//        userUser.setPassword("pass");
//        userRep.save(userUser);
//
//        userAdmin.setRoles(Collections.singleton(new Role("ROLE_ADMIN")));
//        userAdmin.setPassword(bCryptPasswordEncoder.encode(userAdmin.getPassword()));
//        userRep.save(userAdmin);
    }
}
