package com.bookapp.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.bookapp.backend.books",
        entityManagerFactoryRef = "booksEntityManagerFactory",
        transactionManagerRef = "booksTransactionManager"
)
public class BooksDatabaseConfig {
    // Configuración de libros
}
