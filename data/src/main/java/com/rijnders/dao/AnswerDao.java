package com.rijnders.dao;

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

    private static AnswerDao answerDaoInstance;
    private final Connection connection;

    private AnswerDao() {
        connection = new PostgresConnectionSetup().connect();
    }

    public static AnswerDao getInstance() {
        if (answerDaoInstance == null) {
            answerDaoInstance = new AnswerDao();
        }
        return answerDaoInstance;
    }

    @Override
    public Answer get(UUID id) throws SQLException {
        connection.setAutoCommit(false);
        String sql = "" +
                "SELECT * " +
                "FROM answer " +
                "WHERE answerid = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        connection.commit();
        if(resultSet.next())
        return ResultToAnswerConvertor.getInstance().convert(resultSet);
        else
            return null;
    }

    @Override
    public List<Answer> getAll() throws SQLException {
        List<Answer> answers = new ArrayList<>();
        connection.setAutoCommit(false);
        String sql = "" +
                "SELECT * " +
                "FROM answer;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        connection.commit();
        while(resultSet.next()) {
            answers.add(ResultToAnswerConvertor.getInstance().convert(resultSet));
        }
        return answers;
    }

    @Override
    public void save(Answer answer) throws SQLException {
        connection.setAutoCommit(false);
        String sql = "" +
                "INSERT INTO answer (answerid, answerline, iscorrect, questionid) " +
                "VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, answer.getId());
        preparedStatement.setString(2, answer.getAnswerLine());
        preparedStatement.setBoolean(3, answer.isCorrect());
        preparedStatement.setObject(4, answer.getQuestionId() );
        preparedStatement.executeQuery();


    }

    @Override
    public void update(Answer answer) throws SQLException {
        connection.setAutoCommit(false);
        String sql ="UPDATE answer SET answerline = ?, iscorrect = ? WHERE answerid = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, answer.getAnswerLine());
        preparedStatement.setBoolean(2, answer.isCorrect());
        preparedStatement.setObject(3, answer.getId());
        preparedStatement.executeUpdate();
        connection.commit();
    }

    @Override
    public void delete(Answer answer) throws SQLException {
        connection.setAutoCommit(false);
        String sql ="DELETE FROM answer WHERE answerid = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, answer.getId());
        preparedStatement.executeUpdate();
        connection.commit();
    }

    @Override
    public List<Answer> getByParent(UUID id) throws SQLException {
        List<Answer> answers = new ArrayList<>();
        connection.setAutoCommit(false);
        String sql = "" +
                "SELECT * " +
                "FROM answer " +
                "WHERE questionid = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        connection.commit();
       while (resultSet.next()){
           answers.add(ResultToAnswerConvertor.getInstance().convert(resultSet));
        }
       return answers;
    }
}
