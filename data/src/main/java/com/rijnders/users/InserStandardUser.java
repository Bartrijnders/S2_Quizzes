package com.rijnders.users;

import com.rijnders.dbconnection.ConnectionSetup;
import com.rijnders.dbconnection.PostgresConnectionSetup;
import com.rijnders.entityinterfaces.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class InserStandardUser implements UserInsertable {

     private final ConnectionSetup connectionSetup;

    public InserStandardUser() {
        this.connectionSetup = new PostgresConnectionSetup(){
        };
    }

    public int insert(User user){
        String sql = "INSERT INTO \"user\"(userid, username, email, password) " +
                "values (?,?,?,?)";

        int result = 0;

        try(Connection connection = connectionSetup.connect();
            PreparedStatement preparedStatement = connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setObject(1, user.getUserId());
            preparedStatement.setString(2, user.getUserName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());

            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
