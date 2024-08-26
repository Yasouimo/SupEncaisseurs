package com.superviseur.springboot.User.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.superviseur.springboot.User.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByEmail(String email); // Retourner une liste d'utilisateurs
    
}
