package com.skyways.userauth.service;
 
import com.skyways.userauth.model.User;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
 
@Service
public class AuthService {
 
    // Temporary in-memory storage (until we connect database)
    private List<User> users = new ArrayList<>();
    private Long idCounter = 1L;
 
    public String registerUser(User user) {
        // Check empty fields
        if (user.getUsername() == null || user.getEmail() == null
                || user.getPassword() == null) {
            return "All fields are required!";
        }
 
        // Check if username already exists
        for (User u : users) {
            if (u.getUsername().equals(user.getUsername())) {
                return "Username already exists!";
            }
        }
 
        // Save user
        user.setId(idCounter++);
        users.add(user);
        return "User registered successfully: " + user.getUsername();
    }
 
    public String loginUser(User user) {
        // Check empty fields
        if (user.getUsername() == null || user.getPassword() == null) {
            return "Username and password are required!";
        }
 
        // Check if user exists and password matches
        for (User u : users) {
            if (u.getUsername().equals(user.getUsername())
                    && u.getPassword().equals(user.getPassword())) {
                return "Login successful! Welcome " + user.getUsername();
            }
        }
        return "Invalid username or password!";
    }
 
    public List<User> getAllUsers() {
        return users;
    }
}