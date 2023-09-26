package com.bookapp.backend;

import com.bookapp.backend.books.repository.BookRepository;
import com.bookapp.backend.users.model.User;
import com.bookapp.backend.users.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BookRepository bookRepository;

	@PostConstruct
	void init (){
		User felipePizarro = new User("Felipe","Pizarro", "felipe@test.com");
	}

}
