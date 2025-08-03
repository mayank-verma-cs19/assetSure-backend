package com.example.assetSure.service;

import com.example.assetSure.dto.LoginRequest;
import com.example.assetSure.dto.LoginResponse;
import com.example.assetSure.dto.RegisterRequest;
import com.example.assetSure.model.User;
import com.example.assetSure.repository.UserRepository;
import com.example.assetSure.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public LoginResponse login(LoginRequest request) throws Exception {
        Optional<User> userOpt = userRepository.findByUsername(request.getUsername());
        if (userOpt.isEmpty()) {
            throw new Exception("Invalid username or password");
        }

        User user = userOpt.get();

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new Exception("Invalid username or password");
        }

        String token = jwtUtil.generateToken(user.getUsername());

        return new LoginResponse(token);
    }

    public void register(RegisterRequest request) throws Exception {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new Exception("Username already exists");
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setName(request.getName());
        user.setPhone(request.getPhone());
        user.setAddress(request.getAddress());
        // If role is provided; else default to "USER"
        user.setRole(request.getRole() != null ? request.getRole() : "USER");
        userRepository.save(user);
    }

}