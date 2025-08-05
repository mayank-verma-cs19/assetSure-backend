package com.example.assetSure.contoller;

import com.example.assetSure.dto.LoginRequest;
import com.example.assetSure.dto.LoginResponse;
import com.example.assetSure.dto.RegisterRequest;
import com.example.assetSure.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api")
//@CrossOrigin(origins = "http://localhost:3000") // Adjust for frontend origins
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        logger.info("Login request received: {}", loginRequest);
        try {
            LoginResponse response = authService.login(loginRequest);
            logger.info("Login successful for: {}", loginRequest.getUsername());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Login failed for: {} - {}", loginRequest.getUsername(), e.getMessage());
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        logger.info("Register request received: {}", registerRequest);
        try {
            authService.register(registerRequest);
            logger.info("User registered successfully: {}", registerRequest.getUsername());
            return ResponseEntity.ok("User registered successfully!");
        } catch (Exception e) {
            logger.error("Registration failed for: {} - {}", registerRequest.getUsername(), e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
