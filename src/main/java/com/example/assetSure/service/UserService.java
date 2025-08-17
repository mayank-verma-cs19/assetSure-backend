package com.example.assetSure.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.assetSure.dto.UserInfo;
import com.example.assetSure.model.User;
import com.example.assetSure.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired  // Constructor injection of repository
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserInfo getUserInfoByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) return null;
        return new UserInfo(
                user.getId(),
                user.getUsername(),
                user.getName(),
                user.getRole(),
                user.getPhone(),
                user.getAddress()
        );
    }
}
