package com.example.assetSure.service.serviceImpl;

import com.example.assetSure.dto.LoginRequest;
import com.example.assetSure.dto.LoginResponse;
import com.example.assetSure.dto.RegisterRequest;
import com.example.assetSure.model.User;
import com.example.assetSure.repository.UserRepository;
import com.example.assetSure.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public LoginResponse login(LoginRequest request) throws Exception {
        logger.info("Attempting login for username: {}", request.getUsername());

        User userOpt = userRepository.findByUsername(request.getUsername());

        if (userOpt ==  null) {
            logger.warn("Login failed: User not found for username {}", request.getUsername());
            throw new Exception("Invalid username or password");
        }

        User user = userOpt;

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            logger.warn("Login failed: Password mismatch for username {}", request.getUsername());
            throw new Exception("Invalid username or password");
        }

        String token = jwtUtil.generateToken(user.getUsername());
        logger.info("Login successful for username: {} - JWT generated", request.getUsername());

        return new LoginResponse(token , user);
    }

    public void register(RegisterRequest request) throws Exception {
        logger.info("Registration attempt for username: {}", request.getUsername());

        if (userRepository.findByUsername(request.getUsername()) != null) {
            logger.warn("Registration failed: Username already exists - {}", request.getUsername());
            throw new Exception("Username already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setName(request.getName());
        user.setPhone(request.getPhone());
        user.setAddress(request.getAddress());
        user.setRole(request.getRole() != null ? request.getRole() : "USER");

        userRepository.save(user);
        logger.info("User registered successfully with username: {}", request.getUsername());
    }
}
