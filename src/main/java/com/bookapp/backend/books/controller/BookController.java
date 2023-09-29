package com.bookapp.backend.books.controller;

import com.bookapp.backend.books.model.Book;
import com.bookapp.backend.books.repository.BookRepository;
import com.bookapp.backend.users.model.User;
import com.bookapp.backend.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
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

    @GetMapping("/books/{bookId}")
    public ResponseEntity<Book> getBookById(@PathVariable Long bookId) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/user/{userId}/books/{bookId}")
    public ResponseEntity<String> updateBook(@PathVariable Long userId, @PathVariable Long bookId, @RequestBody Book updatedBook) {
        try {
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

            // Verificar si el libro pertenece al usuario (por ejemplo, comparando userId)
            if (!book.getUserId().equals(userId)) {
                return ResponseEntity.badRequest().body("Book does not belong to the user");
            }

            // Actualizar los atributos del libro
            book.setTitolo(updatedBook.getTitolo());
            book.setAutore(updatedBook.getAutore());
            book.setCodiceISBN(updatedBook.getCodiceISBN());
            book.setTrama(updatedBook.getTrama());
            book.setNumeroLetture(updatedBook.getNumeroLetture());

            // Guardar los cambios en el libro
            bookRepository.save(book);

            return ResponseEntity.ok("Book updated successfully");
        } catch (Exception e) {
            e.printStackTrace(); // Registra el error en la consola (puedes personalizar esto según tus necesidades)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    @DeleteMapping("/user/{userId}/book/{bookId}")
    public ResponseEntity<String> deleteBook(@PathVariable Long userId, @PathVariable Long bookId) {
        // Verificar si el libro existe y pertenece al usuario
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isEmpty() || !optionalBook.get().getUserId().equals(userId)) {
            return ResponseEntity.badRequest().body("Book not found or does not belong to the user");
        }

        // Eliminar el libro
        bookRepository.deleteById(bookId);

        return ResponseEntity.ok("Book deleted successfully");
    }


}
