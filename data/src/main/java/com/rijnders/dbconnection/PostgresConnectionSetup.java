package com.rijnders.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresConnectionSetup implements ConnectionSetup {

    private final ConnectionValues connectionValues;

    public PostgresConnectionSetup() {
        this.connectionValues = new ConnectionValues();
    }

    @Override
    public Connection connect() throws SQLException {
        return DriverManager.getConnection(connectionValues.getUrl(), connectionValues.getUser(), connectionValues.getPassword());
    }
}
