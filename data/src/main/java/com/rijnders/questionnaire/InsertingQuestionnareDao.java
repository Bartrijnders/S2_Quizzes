package com.rijnders.questionnaire;

import com.rijnders.dbconnection.ConnectionSetup;
import com.rijnders.dbconnection.PostgresConnectionSetup;
import com.rijnders.entityinterfaces.Questionnair;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertingQuestionnareDao {

    private ConnectionSetup connectionSetup;

    public InsertingQuestionnareDao() {
        this.connectionSetup = new PostgresConnectionSetup();
    }

    public int insertQuetionnaire(Questionnair questionnair) {

        int result;

        String sql = "" +
                "INSERT INTO questionnaire (questionnaireid, userid, name)" +
                "VALUES (?,?,?);";

        try(Connection connection = connectionSetup.connect();
            PreparedStatement preparedStatement = connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setObject(1, questionnair.getId());
            preparedStatement.setObject(2, questionnair.getAuthor().getUserId());
            preparedStatement.setObject(3, questionnair.getName());

            result = preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            result = e.getErrorCode();
        }
        return result;
    }
}
