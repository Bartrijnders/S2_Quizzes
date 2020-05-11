package com.rijnders.questionnaire;

import com.rijnders.dbconnection.ConnectionSetup;
import com.rijnders.dbconnection.PostgresConnectionSetup;
import com.rijnders.entities.StandardQuestion;
import com.rijnders.entityinterfaces.Answer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertAnswerDao {

    private ConnectionSetup connectionSetup;

    public InsertAnswerDao() {
        this.connectionSetup = new PostgresConnectionSetup();
    }

    public int insertAnswer(Answer answer, StandardQuestion question) {

        int result;

        String sql = "" +
                "INSERT INTO answer (answerid, answerline, iscorrect, questionid)" +
                "VALUES (?,?,?,?);";

        try(Connection connection = connectionSetup.connect();
            PreparedStatement preparedStatement = connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setObject(1, answer.getId());
            preparedStatement.setString(2, answer.getAnswerLine());
            preparedStatement.setBoolean(3, answer.isCorrect());
            preparedStatement.setObject(4, question.getId());

            result = preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            result = e.getErrorCode();
        }
        return result;
    }
}
