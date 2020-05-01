package com.rijnders.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresConnectionSetup implements ConncectionSetup {

    private ConnectionValues connectionValues;

    public PostgresConnectionSetup() {
        this.connectionValues = new ConnectionValues();
    }

    @Override
    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(connectionValues.getUrl(), connectionValues.getUser(), connectionValues.getPassword()) ;
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }
}
