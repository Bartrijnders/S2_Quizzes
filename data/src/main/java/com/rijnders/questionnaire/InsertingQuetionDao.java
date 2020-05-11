package com.rijnders.questionnaire;

import com.rijnders.dbconnection.ConnectionSetup;
import com.rijnders.dbconnection.PostgresConnectionSetup;
import com.rijnders.entities.StandardQuestion;
import com.rijnders.entityinterfaces.Questionnair;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertingQuetionDao {

    private ConnectionSetup connectionSetup;

    public InsertingQuetionDao() {
        this.connectionSetup = new PostgresConnectionSetup();
    }

    public int insertQuetionnaire(StandardQuestion question, Questionnair questionnair) {

        int result;

        String sql = "" +
                "INSERT INTO question (questionid, questionnaireid, questionline, type)" +
                "VALUES (?,?,?,?);";

        try(Connection connection = connectionSetup.connect();
            PreparedStatement preparedStatement = connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setObject(1, question.getId());
            preparedStatement.setObject(2, questionnair.getId());
            preparedStatement.setObject(3, question.getQuestionLine());
            preparedStatement.setString(4, question.getType().toString());

            result = preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            result = e.getErrorCode();
        }
        return result;
    }
}
