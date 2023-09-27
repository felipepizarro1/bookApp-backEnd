package com.bookapp.backend.config;

import com.bookapp.backend.books.model.Book;
import com.bookapp.backend.books.repository.BookRepository;
import com.bookapp.backend.users.repository.UserRepository;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackageClasses = BookRepository.class, entityManagerFactoryRef = "bookDSEmFactory", transactionManagerRef = "bookDSTransactionManager" )
@EntityScan(basePackages = {"com.bookapp.backend.books", "com.bookapp.backend.users"})
public class BooksDatabaseConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.secondary")
    public DataSourceProperties bookDSProperties(){
        return new DataSourceProperties();
    }
    @Bean
    public DataSource bookDS(@Qualifier("bookDSProperties") DataSourceProperties bookDSProperties ){
        return bookDSProperties.initializeDataSourceBuilder().build();

    }
    @Bean
    public LocalContainerEntityManagerFactoryBean bookDSEmFactory
            (@Qualifier("bookDS") DataSource bookDS, EntityManagerFactoryBuilder builder){
        return builder.dataSource(bookDS).packages(Book.class).build();
    }
    @Bean
    public PlatformTransactionManager bookDSTransactionManager(EntityManagerFactory bookDSEmFactory){
        return new JpaTransactionManager(bookDSEmFactory);
    }
    // Configuración de libros
}
