package com.example.springAPI.service;

import com.example.springAPI.api.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final List<User> users;

    public UserService() {
        users = new ArrayList<>();

        User user1 = new User(1, "tom", "tom@gmail.com", "apples");
        User user2 = new User(2, "jack", "jack@gmail.com", "bananas");
        User user3 = new User(3, "john", "john@gmail.com", "avocados");
        User user4 = new User(4, "jane", "jane@gmail.com", "grapes");

        users.addAll(Arrays.asList(user1, user2, user3, user4));
    }

    public Optional<User> getUserById(int id) {
        return users.stream().filter(user -> user.getId() == id).findFirst();
    }

    public Optional<User> getUserByUsername(String username) {
        return users.stream().filter(user -> user.getUsername().equals(username)).findFirst();
    }

    public boolean addUser(User user) {
        String username = user.getUsername();
        String email = user.getEmail();

        boolean usernameExists = users.stream().anyMatch(u -> u.getUsername().equals(username));
        boolean emailExists = users.stream().anyMatch(u -> u.getEmail().equals(email));

        if (usernameExists || emailExists) {
            return false;
        } else {
            users.add(user);
            return true;
        }
    }
}
