package com.superviseur.springboot.User.service;

import java.util.List;

import com.superviseur.springboot.Role.model.Role;
import com.superviseur.springboot.User.model.User;

public interface UserService {
    List<User> getAllUsers();
    List<User> getAllUsersExcluding(String email);
    User getUserById(Long id);
    void saveUser(User user);
    void deleteUser(Long id);
    boolean emailExists(String email);
    User getUserByEmail(String email);
    Role getRoleByUserEmail(String email); // Add this method if needed
}
