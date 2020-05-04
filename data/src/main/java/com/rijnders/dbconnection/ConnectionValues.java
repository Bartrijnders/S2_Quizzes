package com.rijnders.dbconnection;

public class ConnectionValues {

    private static final String url = "jdbc:postgresql://localhost:5432/qprojectdb";
    private static final String user = "postgres";
    private static final String password = "password";

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
