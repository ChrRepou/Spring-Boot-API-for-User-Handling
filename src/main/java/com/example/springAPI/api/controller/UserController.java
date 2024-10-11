package com.example.springAPI.api.controller;

import com.example.springAPI.api.model.User;
import com.example.springAPI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{username}")
    @ResponseBody
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        User user = userService.getUserByUsername(username).orElse(null);

        if (user != null) {
            // Return 200 OK with the user object
            return ResponseEntity.ok(user);
        } else {
            // Return 404 Not Found
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/users")
    @ResponseBody
    public ResponseEntity<User> getUserById(@RequestParam int id) {
        User userData = userService.getUserById(id).orElse(null);

        if (userData != null) {
            return ResponseEntity.ok(userData);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
