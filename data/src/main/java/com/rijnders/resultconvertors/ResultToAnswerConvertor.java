package com.rijnders.resultconvertors;

import com.rijnders.entities.StandardAnswer;
import com.rijnders.entityinterfaces.Answer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class ResultToAnswerConvertor implements ResultConvertor<Answer> {


    private static ResultToAnswerConvertor resultToAnswerConvertorInstance;

    private ResultToAnswerConvertor() {
    }

    public static ResultToAnswerConvertor getInstance() {
        if (resultToAnswerConvertorInstance == null) {
            resultToAnswerConvertorInstance = new ResultToAnswerConvertor();
        }
        return resultToAnswerConvertorInstance;
    }

    @Override
    public Answer convert(ResultSet resultSet) throws SQLException {
        String answeridStr = resultSet.getString("userid");
        UUID answerid = UUID.fromString(answeridStr);
        String answerLine = resultSet.getString("answerline");
        boolean isCorrect = resultSet.getBoolean("iscorrect");
        String questionIdStr = resultSet.getString("questionid");
        UUID questionId = UUID.fromString(questionIdStr);

        return new StandardAnswer(answerLine, isCorrect, answerid, questionId);
    }
}
