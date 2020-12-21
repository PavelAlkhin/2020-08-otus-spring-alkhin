package ru.otus.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.otus.spring.models.Book;
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

    @Bean
    DirectChannel outputChannel() {
        return new DirectChannel();
    }
    @MessagingGateway
    public interface BookLabrary {
        @Gateway(requestChannel = "saveBookFlow.input")
        Book saveBook(Book book);
    }
    // канал DirectChannel с именем animalFlow.input создается автоматически
    @Bean
    public IntegrationFlow saveBookFlow() {
        return flow -> flow
                .handle("bookService", "saveBook")
                .channel("outputChannel");
    }

    public static void main(String[] args) {

        SpringApplication.run(Main.class, args);

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
