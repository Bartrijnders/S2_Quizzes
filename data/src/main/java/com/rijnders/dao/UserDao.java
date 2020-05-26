package com.rijnders.dao;

import com.rijnders.dbconnection.PostgresConnectionSetup;
import com.rijnders.entities.StandardUser;
import com.rijnders.entityinterfaces.User;
import com.rijnders.resultconvertors.ResultConvertor;
import com.rijnders.resultconvertors.ResultToAnswerConvertor;
import com.rijnders.resultconvertors.ResultToStandardUserConvertor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserDao implements Dao<User>, DaoByString<User> {

    private static UserDao userDaoInstance;
    private Connection connection;

    private UserDao() {
        this.connection = new PostgresConnectionSetup().connect();
    }

    public static UserDao getInstance() {
        if (userDaoInstance == null) {
            userDaoInstance = new UserDao();
        }
        return userDaoInstance;
    }

    @Override
    public User get(UUID id) throws SQLException{
        User output = null;
        String sql = "" +
                "SELECT * " +
                "FROM \"user\" " +
                "WHERE userid = ?";

        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, id.toString());
        ResultSet resultSet = pstmt.executeQuery();
        if (resultSet.next())
            output = ResultToStandardUserConvertor.getInstance().convert(resultSet);

        connection.close();
        return output;
    }

    @Override
    public List<User> getAll() throws SQLException {
        List<User> output = new ArrayList<>();
        String sql = "" +
                "SELECT * " +
                "FROM \"user\";";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            output.add(ResultToStandardUserConvertor.getInstance().convert(resultSet));
        }
        connection.close();
        return output;
    }

    @Override
    public void save(User user) throws SQLException {
        connection.setAutoCommit(false);
        String sql = "" +
                "INSERT INTO \"user\" " +
                "(userid, username, email, password) " +
                "VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, user.getUserId());
        preparedStatement.setString(2, user.getUserName());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setString(4, user.getPassword());
        preparedStatement.executeUpdate();
        connection.commit();

    }

    @Override
    public void update(User user) throws SQLException {
        connection.setAutoCommit(false);
        String sql = "" +
                "UPDATE \"user\" " +
                "SET username=?, email=?, password=? " +
                "WHERE userid = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user.getUserName());
        preparedStatement.setString(2, user.getEmail());
        preparedStatement.setString(3, user.getPassword());
        preparedStatement.setObject(4, user.getUserId());
        preparedStatement.executeUpdate();
        connection.commit();
    }

    @Override
    public void delete(User user) throws SQLException {
        connection.setAutoCommit(false);
        String sql = "DELETE FROM \"user\" WHERE userid = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, user.getUserId());
        preparedStatement.executeUpdate();
        connection.commit();
    }

    @Override
    public User getByString(String email) {
        User output = null;
        String sql = "SELECT * " +
                "FROM \"user\" " +
                "WHERE email = ?";

        try(PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setString(1, email);
            ResultSet resultSet = pstmt.executeQuery();
            if(resultSet.next())
                output = ResultToStandardUserConvertor.getInstance().convert(resultSet);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return output;
    }
}
