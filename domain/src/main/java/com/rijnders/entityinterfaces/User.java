package com.rijnders.entityinterfaces;

import java.util.UUID;

public interface User {

    UUID getUserId();

    String getUserName();

    String getEmail();

    String getPassword();

    void setUserName(String userName);

    void setEmail(String email);

    void setPassword(String password);
}
