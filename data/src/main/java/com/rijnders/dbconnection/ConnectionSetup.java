package com.rijnders.dbconnection;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionSetup {
    Connection connect() throws SQLException;
}
