package com.skyways.userauth.service;
 
import com.skyways.userauth.model.User;
import com.skyways.userauth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
 
@Service
public class AuthService {
 
    @Autowired
    private UserRepository userRepository;
 
    public String registerUser(User user) {
        // Check empty fields
        if (user.getUsername() == null || user.getEmail() == null
                || user.getPassword() == null) {
            return "All fields are required!";
        }
 
        // Check if username already exists
        if (userRepository.existsByUsername(user.getUsername())) {
            return "Username already exists!";
        }
 
        // Check if email already exists
        if (userRepository.existsByEmail(user.getEmail())) {
            return "Email already exists!";
        }
 
        // Save to database
        userRepository.save(user);
        return "User registered successfully: " + user.getUsername();
    }
 
    public String loginUser(User user) {
        // Check empty fields
        if (user.getUsername() == null || user.getPassword() == null) {
            return "Username and password are required!";
        }
 
        // Find user in database
        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
 
        if (existingUser.isPresent() &&
                existingUser.get().getPassword().equals(user.getPassword())) {
            return "Login successful! Welcome " + user.getUsername();
        }
 
        return "Invalid username or password!";
    }
 
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
 