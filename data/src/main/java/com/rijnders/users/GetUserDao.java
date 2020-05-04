package com.rijnders.users;

import com.rijnders.dbconnection.ConnectionSetup;
import com.rijnders.dbconnection.PostgresConnectionSetup;
import com.rijnders.entityinterfaces.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class GetUserDao {

    private final ConnectionSetup connectionSetup;
    private final ResultToUserConvertor resultToUserConvertor;

    public GetUserDao() {
        this.connectionSetup = new PostgresConnectionSetup();
        this.resultToUserConvertor = new ResultToStandardUserConvertor();
    }


    //gets a user by the given username. returns null if no user is found.
    public User selectUserByUsername(String username){
        User output = null;

        String sql = "SELECT * " +
                "FROM \"user\" " +
                "WHERE user_name = ?";

        try(Connection connection = connectionSetup.connect();
            PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setString(1, username);
            ResultSet resultSet = pstmt.executeQuery();
            output = resultToUserConvertor.convert(resultSet);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return output;
    }

    public List<User> selectUsersByUsername_Email (String username, String email){
        List<User> output = null;

        String sql = "SELECT * " +
                "FROM \"user\" " +
                "WHERE user_name = ?" +
                "OR email = ?";

        try(Connection connection = connectionSetup.connect();
            PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setString(1, username);
            pstmt.setString(2, email);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()){
                output.add(resultToUserConvertor.convert(resultSet));
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return output;
    }

}
