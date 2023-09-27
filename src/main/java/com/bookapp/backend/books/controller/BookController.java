package com.bookapp.backend.books.controller;

import com.bookapp.backend.books.model.Book;
import com.bookapp.backend.books.repository.BookRepository;
import com.bookapp.backend.users.model.User;
import com.bookapp.backend.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

public class BookController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;

    @PostMapping("/add")
    public ResponseEntity<String> addbook(@RequestParam Long userId, @RequestBody Book book){
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()){
            return ResponseEntity.badRequest().body("User not found");
        }
        User user = optionalUser.get();

        book.setUser(user);

        bookRepository.save(book);

        return ResponseEntity.ok("book added correctly");
    }
}
