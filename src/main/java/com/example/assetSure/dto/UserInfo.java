package com.example.assetSure.dto;

public class UserInfo {
    private Long id;
    private String username;
    private String name;
    private String role;
    private String phone;
    private String address;

    public UserInfo() {
    }

    public UserInfo(Long id, String username, String name, String role, String phone, String address) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.role = role;
        this.phone = phone;
        this.address = address;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
