package com.bookapp.backend.books.controller;

import com.bookapp.backend.books.model.Book;
import com.bookapp.backend.books.repository.BookRepository;
import com.bookapp.backend.users.model.User;
import com.bookapp.backend.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;

    @PostMapping("/{userId}/add")
    public ResponseEntity<String> addBook(@PathVariable Long userId, @RequestBody Book book) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found");
        }
        User user = optionalUser.get();

        // Asignar el usuario al libro

        bookRepository.save(book);

        return ResponseEntity.ok("Book added correctly");
    }

    @GetMapping("/user/{userId}/books")
    public ResponseEntity<List<Book>> getUserBooks(@PathVariable Long userId) {
        List<Book> userBooks = bookRepository.findByUserId(userId);

        if (userBooks.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(userBooks);
    }
}
