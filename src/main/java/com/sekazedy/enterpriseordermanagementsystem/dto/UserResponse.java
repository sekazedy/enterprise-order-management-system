package com.sekazedy.enterpriseordermanagementsystem.dto;

public class UserResponse {
    private Long id;
    private String userName;
    private String email;

    public UserResponse(Long id, String userName, String email) {
        this.id = id;
        this.userName = userName;
        this.email = email;
    }

    public Long getId() { return id; }
    public String getUserName() { return userName; }
    public String getEmail() { return email; }
}
