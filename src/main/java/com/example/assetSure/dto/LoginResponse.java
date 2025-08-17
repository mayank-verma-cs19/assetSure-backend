package com.example.assetSure.dto;

import com.example.assetSure.model.User;

public class LoginResponse {
    private String token;
    private User user;  // Add this

    public LoginResponse(String token, User User) {
        this.token = token;
        this.user = User;
    }

    public String getToken() {
        return token;
    }

    public User getUser() {
        return user ;
    }
}
