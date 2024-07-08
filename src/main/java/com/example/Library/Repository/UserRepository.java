package com.example.Library.Repository;

import com.example.Library.Entities.User;
import com.example.Library.Services.UserService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String username);

}
