package com.bookapp.backend.users.repository;

import com.bookapp.backend.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {


}
