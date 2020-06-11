package com.rijnders.dao;

import com.rijnders.dbconnection.PostgresConnectionSetup;
import com.rijnders.entityinterfaces.Turn;
import com.rijnders.resultconvertors.ResultConverter;
import com.rijnders.resultconvertors.ResultToTurnConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TurnDao implements Dao<Turn>, DaoByParent<List<Turn>> {


    @Override
    public Turn get(UUID id) throws SQLException {
        return null;
    }

    @Override
    public List<Turn> getAll() throws SQLException {
        return null;
    }

    @Override
    public void save(Turn turn) throws SQLException {
        String sql = "INSERT INTO turn (number, quizid, questionid, answerLine, iscorrect) VALUES (?,?,?,?,?)";
        try (Connection connection = new PostgresConnectionSetup().connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, turn.getNumber());
            preparedStatement.setObject(2, turn.getQuizId());
            preparedStatement.setObject(3, turn.getQuestion().getId());
            preparedStatement.setString(4, turn.getChosenAnswer());
            preparedStatement.setBoolean(5, turn.isCorrect());
            preparedStatement.executeUpdate();
        }

    }

    @Override
    public void update(Turn turn) throws SQLException {

    }

    @Override
    public void delete(Turn turn) throws SQLException {

    }

    @Override
    public List<Turn> getByParent(UUID id) throws SQLException {
        String sql = "" +
                "SELECT * FROM turn WHERE quizid = ?";
        try (Connection connection = new PostgresConnectionSetup().connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setObject(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultConverter<Turn> resultConverter = new ResultToTurnConverter();
            List<Turn> output = new ArrayList<>();
            while (resultSet.next()) {
                output.add(resultConverter.convert(resultSet));
            }
            return output;
        }
    }
}
