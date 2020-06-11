package com.rijnders.dao;

import com.rijnders.dbconnection.ConnCloser;
import com.rijnders.dbconnection.PostgresConnectionSetup;
import com.rijnders.entityinterfaces.User;
import com.rijnders.resultconvertors.ResultToStandardUserConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserDao implements Dao<User>, DaoByString<User> {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private final String selectEverything = "SELECT *";

    public UserDao() throws SQLException {
        this.connection = new PostgresConnectionSetup().connect();
        preparedStatement = null;
        resultSet = null;
    }


    @Override
    public User get(UUID id) throws SQLException {
        try {
            openConnecton();
            User output = null;
            String sql = "" +
                    selectEverything +
                    "FROM \"user\" " +
                    "WHERE userid = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                output = ResultToStandardUserConverter.getInstance().convert(resultSet);

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
            openConnecton();
            List<User> output = new ArrayList<>();
            String sql = "" +
                    selectEverything +
                    "FROM \"user\";";

            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                output.add(ResultToStandardUserConverter.getInstance().convert(resultSet));
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
            openConnecton();
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
        openConnecton();
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
        try {
            openConnecton();
            if (user != null) {
                connection.setAutoCommit(false);
                String sql = "DELETE FROM \"user\" WHERE userid = ?";

                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setObject(1, user.getUserId());
                preparedStatement.executeUpdate();
                connection.commit();
            }
        } finally {
            ConnCloser.getInstance().closeConnection(connection, preparedStatement, resultSet);
        }
    }


    @Override
    public User getByEmail(String email) throws SQLException {
        openConnecton();
        User output = null;
        String sql = selectEverything +
                "FROM \"user\" " +
                "WHERE email = ?";

        return fillAndExecute(email, output, sql);
    }

    @Override
    public User getByUsername(String username) throws SQLException {
        openConnecton();
        String sql = selectEverything +
                "FROM \"user\" " +
                "WHERE username = ?";
        return fillAndExecute(username, null, sql);
    }

    private void openConnecton() throws SQLException {
        if (connection.isClosed()) {
            connection = new PostgresConnectionSetup().connect();
        }
    }

    private User fillAndExecute(String username, User output, String sql) throws SQLException {
        try {
            openConnecton();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                output = ResultToStandardUserConverter.getInstance().convert(resultSet);
        } finally {
            ConnCloser.getInstance().closeConnection(connection, preparedStatement, resultSet);
        }
        return output;
    }
}
