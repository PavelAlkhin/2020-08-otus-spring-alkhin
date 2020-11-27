package ru.otus.spring.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.otus.spring.models.Role;
import ru.otus.spring.models.User;
import ru.otus.spring.repositories.RoleRepository;
import ru.otus.spring.repositories.UserRepository;

import java.util.Collections;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class InitialFillings {

    @Autowired
    private UserRepository userRep;

    @Autowired
    private RoleRepository roleRep;

    public void fillUsers() {

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
