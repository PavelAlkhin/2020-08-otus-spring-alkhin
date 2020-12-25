package ru.otus.spring.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.otus.spring.models.Role;
import ru.otus.spring.models.User;
import ru.otus.spring.repositories.RoleRepository;
import ru.otus.spring.repositories.UserRepository;

import java.util.Set;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public User saveUser(User user, Role role) {

        Role userRole = roleRepository.findByRole(role.getRole());
        if(userRole == null){
            userRole = roleRepository.save(role);
        }

        User userFromRep = userRepository.findByUserName(user.getUserName());
        if(userFromRep != null){
            return userFromRep;
        }

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        Set<Role> userRoles = user.getRoles();

        if (userRoles.size() == 0){
            userRoles.add(userRole);
        }

        user.setRoles(userRoles);
        return userRepository.save(user);
    }

//    public User saveAdmin(User user) {
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        user.setActive(true);
//        Role userRole = roleRepository.findByRole("ROLE_ADMIN");
//        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
//        return userRepository.save(user);
//    }

}