package ru.diasoft.tutorial.microservices.module8.repository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import ru.diasoft.tutorial.microservices.module8.entity.Role;
import ru.diasoft.tutorial.microservices.module8.entity.User;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
public class UserRepository {

    private final List<User> userList = new ArrayList<>();

    @PostConstruct
    public void init() {
        userList.addAll(Arrays.asList(
                new User("admin", new BCryptPasswordEncoder().encode("admin"), Collections.singletonList(Role.ADMIN), true, true, true, true),
                new User("user", new BCryptPasswordEncoder().encode("password"), Collections.singletonList(Role.USER), true, true, true, true),
                new User("lena", new BCryptPasswordEncoder().encode("123"), Collections.singletonList(Role.ADMIN), true, true, true, true)
        ));
    }

    public Optional<User> findByName(String name) {
        return userList.stream()
                .filter(user -> user.getUsername().equals(name))
                .findFirst();
    }
}
