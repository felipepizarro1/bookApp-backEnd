package com.bookapp.backend.books.controller;

import com.bookapp.backend.books.model.Book;
import com.bookapp.backend.books.repository.BookRepository;
import com.bookapp.backend.users.model.User;
import com.bookapp.backend.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
        book.setUserId(user.getId());
        book.setDataAggiunta(LocalDateTime.now());
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

    @PutMapping("/user/{userId}/book/{bookId}")
    public ResponseEntity<String> updateBook(@PathVariable Long userId, @PathVariable Long bookId, @RequestBody Book updatedBook) {
        // Verificar si el usuario existe
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found");
        }
        User user = optionalUser.get();

        // Verificar si el libro existe
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isEmpty()) {
            return ResponseEntity.badRequest().body("Book not found");
        }
        Book book = optionalBook.get();

        // Verificar si el libro pertenece al usuario
        if (!book.getUser().equals(user)) {
            return ResponseEntity.badRequest().body("Book does not belong to the user");
        }

        // Actualizar los atributos del libro
        book.setTitolo(updatedBook.getTitolo());
        book.setAutore(updatedBook.getAutore());
        // Actualizar otros atributos según sea necesario

        // Guardar los cambios en el libro
        bookRepository.save(book);

        return ResponseEntity.ok("Book updated successfully");
    }

    @DeleteMapping("/user/{userId}/book/{bookId}")
    public ResponseEntity<String> deleteBook(@PathVariable Long userId, @PathVariable Long bookId) {
        // Verificar si el usuario existe
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found");
        }
        User user = optionalUser.get();

        // Verificar si el libro existe
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isEmpty()) {
            return ResponseEntity.badRequest().body("Book not found");
        }
        Book book = optionalBook.get();

        // Verificar si el libro pertenece al usuario
        if (!book.getUser().equals(user)) {
            return ResponseEntity.badRequest().body("Book does not belong to the user");
        }

        // Eliminar el libro
        bookRepository.delete(book);

        return ResponseEntity.ok("Book deleted successfully");
    }


}
