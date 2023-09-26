package com.bookapp.backend.books.repository;

import com.bookapp.backend.books.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
