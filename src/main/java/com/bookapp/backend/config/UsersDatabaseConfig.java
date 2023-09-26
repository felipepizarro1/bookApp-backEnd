package com.bookapp.backend.config;

import com.bookapp.backend.users.model.User;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.EntityManagerFactoryInfo;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories()
public class UsersDatabaseConfig {
    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource.primary")
    public DataSourceProperties userDBProperties(){
        return new DataSourceProperties();

    }
    @Primary
    @Bean
    public DataSource userDS(@Qualifier("userDBProperties") DataSourceProperties userDBProperties){

        return userDBProperties.initializeDataSourceBuilder().build();
    }
    @Bean
    public LocalContainerEntityManagerFactoryBean userDBEmFactory
            (@Qualifier("userDS") DataSource userDS, EntityManagerFactoryBuilder builder){
        return builder.dataSource(userDS).packages(User.class).build();
    }
    @Primary
    @Bean
    public PlatformTransactionManager userDSTransactionManager(EntityManagerFactory factory){
        return new JpaTransactionManager(factory);
    }

}