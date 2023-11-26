package com.example.SpringDemo.services;

import com.example.SpringDemo.dto.UpdateRequest;
import com.example.SpringDemo.models.User;
import com.example.SpringDemo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDetailsService userDetailsService() {
        return username -> userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }

    public User create(User newUser) {
        return userRepository.save(newUser);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }
    
    public User getById(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + id));
    }
    
    public User getByUsername(String username) {
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }
    
    public void deleteByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        userRepository.delete(user);
    }
    
    public User updateUser(UpdateRequest updatedUser) {
        User user = userRepository.findByUsername(updatedUser.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + updatedUser.getUsername()));

        user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        user.setFullname(updatedUser.getFullname());

        return userRepository.save(user);
    }
}