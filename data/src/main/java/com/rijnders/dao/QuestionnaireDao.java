package com.rijnders.dao;

import com.rijnders.dbconnection.ConnCloser;
import com.rijnders.dbconnection.PostgresConnectionSetup;
import com.rijnders.entities.StandardQuestion;
import com.rijnders.entityinterfaces.Questionnaire;
import com.rijnders.resultconvertors.ResultToQuestinnaireConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class QuestionnaireDao implements Dao<Questionnaire>, DaoByParent<List<Questionnaire>> {

    private Connection connection;
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
    public Questionnaire get(UUID id) throws SQLException {
        try {
            connection = new PostgresConnectionSetup().connect();
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
    public List<Questionnaire> getAll() throws SQLException {
        try {
            connection = new PostgresConnectionSetup().connect();
            List<Questionnaire> questionnaires = new ArrayList<>();
            connection.setAutoCommit(false);
            String sql = "" +
                    selectEverything +
                    "FROM questionnaire ;";

            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            connection.commit();
            while (resultSet.next()) {
                questionnaires.add(ResultToQuestinnaireConverter.getInstance().convertToStandard(resultSet));
            }
            return questionnaires;
        }
        finally {
            ConnCloser.getInstance().closeConnection(connection,preparedStatement,resultSet);
        }
    }

    @Override
    public void save(Questionnaire questionnaire) throws SQLException {
        try {
            connection = new PostgresConnectionSetup().connect();
            connection.setAutoCommit(false);
            String sql = "INSERT INTO questionnaire " +
                    "(questionnaireid, userid, name) " +
                    "VALUES (?,?,?);";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, questionnaire.getId());
            preparedStatement.setObject(2, questionnaire.getAuthor().getUserId());
            preparedStatement.setString(3, questionnaire.getName());
            preparedStatement.executeUpdate();
            connection.commit();
            questionDao = new QuestionDao();
            for (StandardQuestion question : questionnaire.getQuestions()) {
                questionDao.save(question);
            }
        }
        finally {
            ConnCloser.getInstance().closeConnection(connection,preparedStatement,resultSet);
        }
    }

    @Override
    public void update(Questionnaire questionnaire) throws SQLException {
        try {
            connection = new PostgresConnectionSetup().connect();
            connection.setAutoCommit(false);
            String sql = "" +
                    "UPDATE questionnaire " +
                    "SET name=? " +
                    "WHERE questionnaireid = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, questionnaire.getName());
            preparedStatement.setObject(2, questionnaire.getId());
            preparedStatement.executeUpdate();
            connection.commit();
            questionDao = new QuestionDao();
            for (StandardQuestion question : questionnaire.getQuestions()) {
                questionDao.update(question);
            }
        }
        finally {
            ConnCloser.getInstance().closeConnection(connection,preparedStatement,resultSet);
        }
    }

    @Override
    public void delete(Questionnaire questionnaire) throws SQLException {
        try {
            connection = new PostgresConnectionSetup().connect();
            connection.setAutoCommit(false);
            String sql = "DELETE" +
                    " FROM questionnaire " +
                    "WHERE questionnaireid = ?;";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, questionnaire.getId());
            preparedStatement.executeUpdate();
            connection.commit();
            questionDao = new QuestionDao();
            for (StandardQuestion question : questionnaire.getQuestions()) {
                questionDao.delete(question);
            }
        }
        finally {
            ConnCloser.getInstance().closeConnection(connection,preparedStatement,resultSet);
        }
    }

    @Override
    public List<Questionnaire> getByParent(UUID id) throws SQLException {
        List<Questionnaire> questionnaires = new ArrayList<>();
        try {
            connection = new PostgresConnectionSetup().connect();
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
