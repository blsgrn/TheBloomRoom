package com.example.thebloomroom_105_53;

public class UserAccount {
    private int userId;
    private String username;

    private String email;

    public UserAccount(int userId, String username, String email) {
        this.userId = userId;
        this.username = username;
        this.email = email;
    }

    // getters and setters
    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }


}
