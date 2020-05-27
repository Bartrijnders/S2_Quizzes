package com.rijnders.dao;

import com.rijnders.dbconnection.ConnCloser;
import com.rijnders.dbconnection.PostgresConnectionSetup;
import com.rijnders.entityinterfaces.User;
import com.rijnders.resultconvertors.ResultToStandardUserConvertor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserDao implements Dao<User>, DaoByString<User> {

    private final Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private final String selectEverything = "SELECT *";
    public UserDao() {
        this.connection = new PostgresConnectionSetup().connect();
        preparedStatement = null;
        resultSet = null;
    }


    @Override
    public User get(UUID id) throws SQLException{
        try {

            User output = null;
            String sql = "" +
                    selectEverything +
                    "FROM \"user\" " +
                    "WHERE userid = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id.toString());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                output = ResultToStandardUserConvertor.getInstance().convert(resultSet);

            connection.close();
            return output;
        }
        finally {
            ConnCloser.getInstance().closeConnection(connection, preparedStatement, resultSet);
        }

    }

    @Override
    public List<User> getAll() throws SQLException {
        try{
            List<User> output = new ArrayList<>();
            String sql = "" +
                    selectEverything +
                    "FROM \"user\";";

            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                output.add(ResultToStandardUserConvertor.getInstance().convert(resultSet));
            }
            connection.close();
            return output;
        }
        finally {
            ConnCloser.getInstance().closeConnection(connection, preparedStatement, resultSet);
        }

    }

    @Override
    public void save(User user) throws SQLException {
        try{
            connection.setAutoCommit(false);
            String sql = "" +
                    "INSERT INTO \"user\" " +
                    "(userid, username, email, password) " +
                    "VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, user.getUserId());
            preparedStatement.setString(2, user.getUserName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.executeUpdate();
            connection.commit();
        }
        finally {
            ConnCloser.getInstance().closeConnection(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public void update(User user) throws SQLException {
        connection.setAutoCommit(false);
        String sql = "" +
                "UPDATE \"user\" " +
                "SET username=?, email=?, password=? " +
                "WHERE userid = ?";

        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user.getUserName());
        preparedStatement.setString(2, user.getEmail());
        preparedStatement.setString(3, user.getPassword());
        preparedStatement.setObject(4, user.getUserId());
        preparedStatement.executeUpdate();
        connection.commit();
    }

    @Override
    public void delete(User user) throws SQLException {
        try{
            connection.setAutoCommit(false);
            String sql = "DELETE FROM \"user\" WHERE userid = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, user.getUserId());
            preparedStatement.executeUpdate();
            connection.commit();
        }
        finally {
            ConnCloser.getInstance().closeConnection(connection, preparedStatement, resultSet);
        }
    }


    @Override
    public User getByEmail(String email) throws SQLException {
        User output = null;
        String sql = selectEverything +
                "FROM \"user\" " +
                "WHERE email = ?";

        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
                output = ResultToStandardUserConvertor.getInstance().convert(resultSet);
        }
        finally {
            ConnCloser.getInstance().closeConnection(connection, preparedStatement, resultSet);
        }
        return output;
    }

    @Override
    public User getByUsername(String username) throws SQLException {
        User output = null;
        String sql = selectEverything +
                "FROM \"user\" " +
                "WHERE username = ?";
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
                output = ResultToStandardUserConvertor.getInstance().convert(resultSet);
        }
        finally {
            ConnCloser.getInstance().closeConnection(connection, preparedStatement, resultSet);
        }
        return output;
    }
}
