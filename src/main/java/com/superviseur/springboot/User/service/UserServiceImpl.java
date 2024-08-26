package com.superviseur.springboot.User.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.superviseur.springboot.Role.model.Role;
import com.superviseur.springboot.User.model.User;
import com.superviseur.springboot.User.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getAllUsersExcluding(String email) {
        return userRepository.findAll().stream()
            .filter(user -> !user.getEmail().equals(email))
            .collect(Collectors.toList());
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = getUserById(id);
        if (user != null) {
            if (user.getEmail().equals("yahyabellmir@gmail.com")) {
                throw new IllegalStateException("Cannot delete the admin account");
            }
            userRepository.deleteById(id);
        } else {
            throw new UsernameNotFoundException("User not found with id: " + id);
        }
    }

    @Override
    public boolean emailExists(String email) {
        return !userRepository.findByEmail(email).isEmpty();
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).stream().findFirst().orElse(null);
    }
    @Override
    public Role getRoleByUserEmail(String email) {
        User user = getUserByEmail(email);
        return user != null ? user.getRole() : null;
    }

}

