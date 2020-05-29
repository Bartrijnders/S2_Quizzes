package com.rijnders.dbconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnCloser {


    private static ConnCloser connCloserInstance;

    private ConnCloser() {
    }

    public static ConnCloser getInstance() {
        if (connCloserInstance == null) {
            connCloserInstance = new ConnCloser();
        }
        return connCloserInstance;
    }

    public void closeConnection(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) throws SQLException {
        if(resultSet != null){
            resultSet.close();
        }
        if(preparedStatement != null){
            preparedStatement.close();
        }
        if(connection != null){
            connection.close();
        }
    }
}
