package com.rijnders.dao;

import com.rijnders.dbconnection.ConnCloser;
import com.rijnders.dbconnection.PostgresConnectionSetup;
import com.rijnders.entityinterfaces.Answer;
import com.rijnders.resultconvertors.ResultToAnswerConvertor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AnswerDao implements Dao<Answer>, DaoByParent<List<Answer>> {

    private final Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private String selectEverything = "SELECT * ";

    public AnswerDao() {
        connection = new PostgresConnectionSetup().connect();
    }



    @Override
    public Answer get(UUID id) throws SQLException {
        try{
            connection.setAutoCommit(false);
            String sql = "" +
                    selectEverything +
                    "FROM answer " +
                    "WHERE answerid = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, id);
            resultSet = preparedStatement.executeQuery();
            connection.commit();
            if(resultSet.next())
                return ResultToAnswerConvertor.getInstance().convert(resultSet);
            else
                return null;
        }
        finally {
            ConnCloser.getInstance().closeConnection(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public List<Answer> getAll() throws SQLException {
        try{
            List<Answer> answers = new ArrayList<>();
            connection.setAutoCommit(false);
            String sql = "" +
                    selectEverything +
                    "FROM answer;";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            connection.commit();
            while(resultSet.next()) {
                answers.add(ResultToAnswerConvertor.getInstance().convert(resultSet));
            }
            return answers;
        }
        finally {
            ConnCloser.getInstance().closeConnection(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public void save(Answer answer) throws SQLException {
        try{
            connection.setAutoCommit(false);
            String sql = "" +
                    "INSERT INTO answer (answerid, answerline, iscorrect, questionid) " +
                    "VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, answer.getId());
            preparedStatement.setString(2, answer.getAnswerLine());
            preparedStatement.setBoolean(3, answer.isCorrect());
            preparedStatement.setObject(4, answer.getQuestionId() );
            preparedStatement.executeQuery();
        }
        finally {
            ConnCloser.getInstance().closeConnection(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public void update(Answer answer) throws SQLException {
        try{
            connection.setAutoCommit(false);
            String sql ="UPDATE answer SET answerline = ?, iscorrect = ? WHERE answerid = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, answer.getAnswerLine());
            preparedStatement.setBoolean(2, answer.isCorrect());
            preparedStatement.setObject(3, answer.getId());
            preparedStatement.executeUpdate();
            connection.commit();
        }
        finally {
            ConnCloser.getInstance().closeConnection(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public void delete(Answer answer) throws SQLException {
        try{
            connection.setAutoCommit(false);
            String sql ="DELETE FROM answer WHERE answerid = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, answer.getId());
            preparedStatement.executeUpdate();
            connection.commit();
        } finally {
            ConnCloser.getInstance().closeConnection(connection, preparedStatement, resultSet);
        }

    }

    @Override
    public List<Answer> getByParent(UUID id) throws SQLException {
        try{
            List<Answer> answers = new ArrayList<>();
            connection.setAutoCommit(false);
            String sql = "" +
                    "SELECT * " +
                    "FROM answer " +
                    "WHERE questionid = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, id);
            resultSet = preparedStatement.executeQuery();
            connection.commit();
            while (resultSet.next()){
                answers.add(ResultToAnswerConvertor.getInstance().convert(resultSet));
            }
            return answers;
        }
        finally {
            ConnCloser.getInstance().closeConnection(connection, preparedStatement, resultSet);
        }
    }
}
