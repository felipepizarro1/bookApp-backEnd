package com.bookapp.backend.users.controller;

import com.bookapp.backend.users.model.User;
import com.bookapp.backend.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody User newUser) {
        User createdUser = userRepository.save(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }
}
