package com.skyways.userauth.controller;
 
import com.skyways.userauth.model.User;
import com.skyways.userauth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
 
@RestController
@RequestMapping("/api/auth")
public class AuthController {
 
    @Autowired
    private AuthService authService;
 
    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("SkyWays User Auth Service is running!");
    }
 
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        String result = authService.registerUser(user);
        if (result.contains("successfully")) {
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
 
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        String result = authService.loginUser(user);
        if (result.contains("successful")) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }
 
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(authService.getAllUsers());
    }
}