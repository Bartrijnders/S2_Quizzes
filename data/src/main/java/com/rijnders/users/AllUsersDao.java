package com.rijnders.users;

import com.rijnders.dbconnection.ConnectionSetup;
import com.rijnders.dbconnection.PostgresConnectionSetup;
import com.rijnders.entityinterfaces.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AllUsersDao {

    private final ConnectionSetup conncection;
    private final ResultToUserConvertor resultToUserConvertor;
    private final String getAllSQL = "SELECT * FROM \"user\";";

    public AllUsersDao() {
        this.conncection = new PostgresConnectionSetup();
        this.resultToUserConvertor = new ResultToStandardUserConvertor();
    }

    public List<User> selectAllUsers(){
        List<User> toReturn = new ArrayList<>();
        try(Connection con = conncection.connect();
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(getAllSQL)){

            while (resultSet.next()){
                toReturn.add(resultToUserConvertor.convert(resultSet));
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return toReturn;
    }
}
