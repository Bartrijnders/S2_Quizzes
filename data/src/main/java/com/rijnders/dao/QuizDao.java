package com.rijnders.dao;

import com.rijnders.dbconnection.PostgresConnectionSetup;
import com.rijnders.entityinterfaces.Quiz;
import com.rijnders.entityinterfaces.Turn;
import com.rijnders.resultconvertors.ResultToQuizConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class QuizDao implements Dao<Quiz>, DaoByParent<List<Quiz>>, QuizDaoByQuestionnaire {

    private Connection connection;

    @Override
    public Quiz get(UUID id) throws SQLException {
        return null;
    }

    @Override
    public List<Quiz> getAll() throws SQLException {
        return null;
    }

    @Override
    public void save(Quiz quiz) throws SQLException {
        connection = new PostgresConnectionSetup().connect();
        connection.setAutoCommit(false);
        String sql = "" +
                "INSERT INTO quiz " +
                "(quizid, questionnaireid, userid, score, createdat) " +
                "VALUES (?, ?, ?, ?, ?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, quiz.getId());
        preparedStatement.setObject(2, quiz.getQuestionnaire().getId());
        preparedStatement.setObject(3, quiz.getParticipant().getUserId());
        preparedStatement.setInt(4, quiz.getScore());
        preparedStatement.setObject(5, quiz.getTime());
        preparedStatement.executeUpdate();
        connection.commit();
        Dao<Turn> turnDao = new TurnDao();
        for (Turn turn : quiz.getTurns()) {
            turnDao.save(turn);
        }
    }

    @Override
    public void update(Quiz quiz) throws SQLException {
        //
    }

    @Override
    public void delete(Quiz quiz) throws SQLException {
        connection = new PostgresConnectionSetup().connect();

        String sql = "" +
                "DELETE FROM quiz WHERE quizid = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, quiz.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public List<Quiz> getByParent(UUID userID) throws SQLException {
        connection = new PostgresConnectionSetup().connect();
        String sql = "SELECT * FROM quiz WHERE userid = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, userID);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Quiz> quizzes = new ArrayList<>();
        while (resultSet.next()) {
            quizzes.add(ResultToQuizConverter.getInstance().convert(resultSet));
        }
        return quizzes;
    }

    @Override
    public List<Quiz> getByQuestionnaire(UUID questionnaireId) throws SQLException {
        connection = new PostgresConnectionSetup().connect();
        String sql = "SELECT * FROM quiz WHERE questionnaireid = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, questionnaireId);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Quiz> quizzes = new ArrayList<>();
        while (resultSet.next()) {
            quizzes.add(ResultToQuizConverter.getInstance().convert(resultSet));
        }
        return quizzes;
    }

}
