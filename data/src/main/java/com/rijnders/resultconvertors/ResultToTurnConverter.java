package com.rijnders.resultconvertors;

import com.rijnders.dao.Dao;
import com.rijnders.dao.QuestionDao;
import com.rijnders.entities.StandardQuestion;
import com.rijnders.entities.StandardTurn;
import com.rijnders.entityinterfaces.Turn;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class ResultToTurnConverter implements ResultConverter<Turn> {
    @Override
    public Turn convert(ResultSet resultSet) throws SQLException {
        int number = resultSet.getInt("number");
        UUID quizId = resultSet.getObject("quizid", UUID.class);
        UUID questionId = resultSet.getObject("questionid", UUID.class);
        String answerLine = resultSet.getString("answerline");
        boolean isCorrect = resultSet.getBoolean("iscorrect");
        Dao<StandardQuestion> questionDao = new QuestionDao();
        StandardQuestion question = questionDao.get(questionId);
        return new StandardTurn(number, question, answerLine, isCorrect, quizId);
    }
}
