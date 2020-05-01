package com.rijnders.entities;

import com.rijnders.entityinterfaces.User;

import java.util.UUID;

public class StandardUser implements User {

    private UUID userId;
    private String userName;
    private String email;
    private String password;

    public StandardUser(UUID userId,String userName, String email, String password) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public StandardUser(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.userId = UUID.randomUUID();
    }

    public UUID getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
