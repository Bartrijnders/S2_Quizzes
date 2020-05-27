package com.rijnders.dao;

import com.rijnders.dbconnection.ConnCloser;
import com.rijnders.dbconnection.PostgresConnectionSetup;
import com.rijnders.entities.StandardQuestion;
import com.rijnders.entityinterfaces.Answer;
import com.rijnders.resultconvertors.ResultToQuestionConvertor;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class QuestionDao implements DaoByParent<List<StandardQuestion>>, Dao<StandardQuestion>{

    private final Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public QuestionDao() {
        this.connection = new PostgresConnectionSetup().connect();
    }


    @Override
    public StandardQuestion get(UUID id) throws SQLException {
        try{
            connection.setAutoCommit(false);
            String sql = "SELECT * FROM question WHERE questionid = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, id);
            resultSet = preparedStatement.executeQuery();
            connection.commit();
            if(resultSet.next()){
                return ResultToQuestionConvertor.getInstance().convert(resultSet);
            }
            return null;
        }
        finally {
            ConnCloser.getInstance().closeConnection(connection, preparedStatement, resultSet);
        }

    }

    @Override
    public List<StandardQuestion> getAll() throws SQLException {
        try {
            connection.setAutoCommit(false);
            List<StandardQuestion> questions = new ArrayList<>();
            String sql = "SELECT * FROM question;";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            connection.commit();
            while(resultSet.next()){
                questions.add(ResultToQuestionConvertor.getInstance().convert(resultSet));
            }
            return questions;
        }
        finally {
            ConnCloser.getInstance().closeConnection(connection, preparedStatement, resultSet);
        }

    }

    @Override
    public void save(StandardQuestion question) throws SQLException {
        try {
            connection.setAutoCommit(false);
            String sql = "" +
                    "INSERT INTO question " +
                    "(questionid, questionnaireid, questionline, type) " +
                    "VALUES (?, ?, ?, ?);";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, question.getId());
            preparedStatement.setObject(2, question.getQuestionnairId());
            preparedStatement.setString(3, question.getQuestionLine());
            preparedStatement.setString(4, question.getType().toString());
            preparedStatement.executeUpdate();
            connection.commit();
            AnswerDao answerDao = new AnswerDao();
            for(Answer answer : question.getAnswers()){
                answerDao.save(answer);
            }
        }
        finally {
            ConnCloser.getInstance().closeConnection(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public void update(StandardQuestion question) throws SQLException {
        try {
            connection.setAutoCommit(false);
            String sql = "" +
                    "UPDATE question SET questionline=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, question.getQuestionLine());
            preparedStatement.executeUpdate();
            connection.commit();
            AnswerDao answerDao = new AnswerDao();
            for(Answer answer : question.getAnswers()){
                answerDao.update(answer);
            }
        }
        finally {
            ConnCloser.getInstance().closeConnection(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public void delete(StandardQuestion question) throws SQLException {
        try{
            AnswerDao answerDao = new AnswerDao();
            for(Answer answer : question.getAnswers()){
                answerDao.delete(answer);
            }
            connection.setAutoCommit(false);
            String sql = "" +
                    "DELETE FROM question " +
                    "WHERE questionid=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, question.getId());
            preparedStatement.executeUpdate();
            connection.commit();
        }
        finally {
            ConnCloser.getInstance().closeConnection(connection, preparedStatement, resultSet);
        }

    }

    @Override
    public List<StandardQuestion> getByParent(UUID id) throws SQLException {
        try{
            connection.setAutoCommit(false);
            List<StandardQuestion> questions = new ArrayList<>();
            String sql = "SELECT * FROM question WHERE questionnaireid = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, id);
            resultSet = preparedStatement.executeQuery();
            connection.commit();
            while(resultSet.next()){
                questions.add(ResultToQuestionConvertor.getInstance().convert(resultSet));
            }
            return questions;
        }
        finally {
            ConnCloser.getInstance().closeConnection(connection, preparedStatement, resultSet);
        }

    }
}
