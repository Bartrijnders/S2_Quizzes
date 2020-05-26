package com.rijnders.users;

import com.rijnders.dbconnection.ConnectionSetup;
import com.rijnders.dbconnection.PostgresConnectionSetup;
import com.rijnders.entities.StandardUser;
import com.rijnders.entityinterfaces.User;
import com.rijnders.resultconvertors.ResultConvertor;
import com.rijnders.resultconvertors.ResultToStandardUserConvertor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SelectUserDao {

    private final ConnectionSetup connectionSetup;
    private final ResultConvertor resultToUserConvertor;

    public SelectUserDao() {
        this.connectionSetup = new PostgresConnectionSetup();
        this.resultToUserConvertor = new ResultToStandardUserConvertor();
    }


    //gets a user by the given username. returns null if no user is found.
    public User selectUserByUsername(String username){
        User output = null;

        String sql = "SELECT * " +
                "FROM \"user\" " +
                "WHERE username = ?";

        try(Connection connection = connectionSetup.connect();
            PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setString(1, username);
            ResultSet resultSet = pstmt.executeQuery();
            if(resultSet.next())
            output = (StandardUser) resultToUserConvertor.convert(resultSet);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return output;
    }

    public List<User> selectUsersByUsername_Email (String username, String email){
        List<User> output = new ArrayList<>();

        String sql = "SELECT * " +
                "FROM \"user\" " +
                "WHERE username = ?" +
                "OR email = ?";

        try(Connection connection = connectionSetup.connect();
            PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setString(1, username);
            pstmt.setString(2, email);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()){
                output.add((User) resultToUserConvertor.convert(resultSet));
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return output;
    }

    public User selectUserByEmail(String email){
        User output = null;

        String sql = "SELECT * " +
                "FROM \"user\" " +
                "WHERE email = ?";

        try(Connection connection = connectionSetup.connect();
            PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setString(1, email);
            ResultSet resultSet = pstmt.executeQuery();
            if(resultSet.next())
            output =(User) resultToUserConvertor.convert(resultSet);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return output;
    }

}
