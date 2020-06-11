package com.rijnders.resultconvertors;

import com.rijnders.entities.StandardAnswer;
import com.rijnders.entityinterfaces.Answer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@SuppressWarnings("SpellCheckingInspection")
public class ResultToAnswerConverter implements ResultConverter<Answer> {


    private static ResultToAnswerConverter resultToAnswerConverterInstance;

    private ResultToAnswerConverter() {
    }

    public static ResultToAnswerConverter getInstance() {
        if (resultToAnswerConverterInstance == null) {
            resultToAnswerConverterInstance = new ResultToAnswerConverter();
        }
        return resultToAnswerConverterInstance;
    }

    @Override
    public Answer convert(ResultSet resultSet) throws SQLException {
        UUID answerid = resultSet.getObject("answerid", UUID.class);
        String answerLine = resultSet.getString("answerline");
        boolean isCorrect = resultSet.getBoolean("iscorrect");
        UUID questionId = resultSet.getObject("questionid", UUID.class);
        return new StandardAnswer(answerLine, isCorrect, answerid, questionId);
    }
}
