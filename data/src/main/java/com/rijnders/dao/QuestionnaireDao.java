package com.rijnders.dao;

import com.rijnders.dbconnection.ConnCloser;
import com.rijnders.dbconnection.PostgresConnectionSetup;
import com.rijnders.entities.StandardQuestion;
import com.rijnders.entityinterfaces.Questionnair;
import com.rijnders.resultconvertors.ResultToQuestinnaireConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class QuestionnaireDao implements Dao<Questionnair>, DaoByParent<List<Questionnair>>{

    private final Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private final String selectEverything = "SELECT * ";
    private Dao<StandardQuestion> questionDao;

    public QuestionnaireDao() {
        connection = new PostgresConnectionSetup().connect();
        preparedStatement = null;
        resultSet = null;
    }

    @Override
    public Questionnair get(UUID id) throws SQLException {
        try{
            connection.setAutoCommit(false);
            String sql = "" +
                    selectEverything +
                    "FROM questionnaire " +
                    "WHERE questionnaireid = ?;";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, id);
            resultSet = preparedStatement.executeQuery();
            connection.commit();
            if(resultSet.next()){
               return ResultToQuestinnaireConverter.getInstance().convertToStandard(resultSet);
            }
            return null;
        }
        finally {
            ConnCloser.getInstance().closeConnection(connection,preparedStatement,resultSet);
        }
    }

    @Override
    public List<Questionnair> getAll() throws SQLException {
        try{
            List<Questionnair> questionnairs = new ArrayList<>();
            connection.setAutoCommit(false);
            String sql = "" +
                    selectEverything +
                    "FROM questionnaire ;";

            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            connection.commit();
            while(resultSet.next()){
                questionnairs.add(ResultToQuestinnaireConverter.getInstance().convertToStandard(resultSet));
            }
            return questionnairs;
        }
        finally {
            ConnCloser.getInstance().closeConnection(connection,preparedStatement,resultSet);
        }
    }

    @Override
    public void save(Questionnair questionnair) throws SQLException {
        try{
            connection.setAutoCommit(false);
            String sql = "INSERT INTO questionnaire " +
                    "(questionnaireid, userid, name) " +
                    "VALUES (?,?,?);";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, questionnair.getId());
            preparedStatement.setObject(2, questionnair.getAuthor().getUserId());
            preparedStatement.setString(3, questionnair.getName());
            preparedStatement.executeUpdate();
            connection.commit();
            questionDao = new QuestionDao();
            for(StandardQuestion question : questionnair.getQuestions()){
                questionDao.save(question);
            }
        }
        finally {
            ConnCloser.getInstance().closeConnection(connection,preparedStatement,resultSet);
        }
    }

    @Override
    public void update(Questionnair questionnair) throws SQLException {
        try{
            connection.setAutoCommit(false);
            String sql = "" +
                    "UPDATE questionnaire " +
                    "SET name=? " +
                    "WHERE questionnaireid = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, questionnair.getName());
            preparedStatement.setObject(2, questionnair.getId());
            preparedStatement.executeUpdate();
            connection.commit();
            questionDao = new QuestionDao();
            for(StandardQuestion question : questionnair.getQuestions()){
                questionDao.update(question);
            }
        }
        finally {
            ConnCloser.getInstance().closeConnection(connection,preparedStatement,resultSet);
        }
    }

    @Override
    public void delete(Questionnair questionnair) throws SQLException {
        try{
            connection.setAutoCommit(false);
            String sql = "DELETE" +
                    " FROM questionnaire " +
                    "WHERE questionnaireid = ?;";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, questionnair.getId());
            preparedStatement.executeUpdate();
            connection.commit();
            questionDao = new QuestionDao();
            for(StandardQuestion question : questionnair.getQuestions()){
                questionDao.delete(question);
            }
        }
        finally {
            ConnCloser.getInstance().closeConnection(connection,preparedStatement,resultSet);
        }
    }

    @Override
    public List<Questionnair> getByParent(UUID id) throws SQLException {
        List<Questionnair> questionnaires = new ArrayList<>();
        try{
            connection.setAutoCommit(false);
            String sql = "" +
                    selectEverything +
                    "FROM questionnaire " +
                    "WHERE userid = ?;";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, id);
            resultSet = preparedStatement.executeQuery();
            connection.commit();
            while (resultSet.next()){
               questionnaires.add(ResultToQuestinnaireConverter.getInstance().convertToStandard(resultSet));
            }
            return questionnaires;
        }
        finally {
            ConnCloser.getInstance().closeConnection(connection,preparedStatement,resultSet);
        }
    }
}
