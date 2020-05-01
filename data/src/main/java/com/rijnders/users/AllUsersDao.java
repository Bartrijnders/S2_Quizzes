package com.rijnders.users;

import com.rijnders.dbconnection.ConncectionSetup;
import com.rijnders.dbconnection.PostgresConnectionSetup;
import com.rijnders.entities.StandardUser;
import com.rijnders.entityinterfaces.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AllUsersDao {

    private final ConncectionSetup conncection;
    private final String getAllSQL = "SELECT * FROM \"user\";";

    public AllUsersDao() {
        this.conncection = new PostgresConnectionSetup();
    }

    public List<User> selectAllUsers(){
        List<User> toReturn = new ArrayList<>();
        try(Connection con = conncection.connect();
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(getAllSQL)){

            while (resultSet.next()){
                String userIdStr = resultSet.getString("user_id");
                UUID userId = UUID.fromString(userIdStr);
                String userName = resultSet.getString("user_name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                toReturn.add(new StandardUser(userId, userName,email,password));
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return toReturn;
    }
}
