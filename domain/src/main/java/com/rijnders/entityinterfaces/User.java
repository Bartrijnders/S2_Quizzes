package com.rijnders.entityinterfaces;

public interface User {
    String getUserName();

    String getEmail();

    String getPassword();

    void setUserName(String userName);

    void setEmail(String email);

    void setPassword(String password);
}
