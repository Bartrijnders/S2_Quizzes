package com.rijnders.questionnaire;

import com.rijnders.dbconnection.ConnectionSetup;
import com.rijnders.dbconnection.PostgresConnectionSetup;
import com.rijnders.entities.*;
import com.rijnders.entityinterfaces.Answer;
import com.rijnders.entityinterfaces.Questionnair;
import com.rijnders.entityinterfaces.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class QuestionnaireDao {

    private ConnectionSetup connectionSetup;

    public QuestionnaireDao() {
        this.connectionSetup = new PostgresConnectionSetup();
    }

    public List<Questionnair> selectAllUserQuestionnaire(User user){
        List<Questionnair> output = new ArrayList<>();

        String sql =
                "SELECT * FROM questionnaire " +
                "LEFT JOIN question " +
                "ON questionnaire.questionnaireid = question.questionnaireid " +
                "LEFT JOIN answer " +
                "on question.questionid = answer.questionid " +
                "WHERE questionnaire.userid = ?;";


        try(Connection connection = connectionSetup.connect();
            PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setObject(1, user.getUserId());
            ResultSet resultSet = pstmt.executeQuery();

            Questionnair questionnaire = null;
            StandardQuestion question = null;
            Answer answer = null;


            while (resultSet.next()){
                String idString = resultSet.getString("questionnaireid");
                UUID id = UUID.fromString(idString);
                if(questionnaire == null || questionnaire.getId() != id){
                    String name = resultSet.getString("name");
                    questionnaire = new StandardQuestionnaire(id, name, user);
                    output.add(questionnaire);
                }
                String questionIdString = resultSet.getString("questionid");
                UUID questionId = UUID.fromString(questionIdString);
                if(question == null || question.getId() != questionId){
                    String line = resultSet.getString("questionline");
                    String type = resultSet.getString("type");
                    switch (QuestionType.valueOf(type)){
                        case MULTIPLECHOICE:
                            question = new MultipleChoiceQuestion(line, questionId);
                            break;
                        case OPEN:
                            question = new OpenQuestion(line, questionId);
                            break;
                        default:
                            break;
                    }
                    questionnaire.getQuestions().add(question);
                }
                String answerIdString = resultSet.getString("answerid");
                UUID answerId = UUID.fromString(answerIdString);
                String answerLine = resultSet.getString("answerline");
                Boolean isCorrect = resultSet.getBoolean("iscorrect");
                if(question instanceof MultipleChoiceQuestion){
                    ((MultipleChoiceQuestion) question).getAnswers().add(new StandardAnswer(answerLine,isCorrect,answerId));
                } else
                    if(question instanceof OpenQuestion){
                        ((OpenQuestion) question).setAnswer(new StandardAnswer(answerLine, isCorrect, answerId));
                    }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return output;
    }
}
