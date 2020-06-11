package com.rijnders.resultconvertors;

import com.rijnders.dao.*;
import com.rijnders.entities.StandardQuiz;
import com.rijnders.entityinterfaces.Questionnaire;
import com.rijnders.entityinterfaces.Turn;
import com.rijnders.entityinterfaces.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public class ResultToQuizConverter implements ResultConverter<StandardQuiz> {


    private static ResultToQuizConverter resultToQuizConverterInstance;

    private ResultToQuizConverter() {
    }

    public static ResultToQuizConverter getInstance() {
        if (resultToQuizConverterInstance == null) {
            resultToQuizConverterInstance = new ResultToQuizConverter();
        }
        return resultToQuizConverterInstance;
    }

    @Override
    public StandardQuiz convert(ResultSet resultSet) throws SQLException {
        String quizIdStr = resultSet.getString("quizid");
        UUID quizId = UUID.fromString(quizIdStr);
        String questionnaireIdStr = resultSet.getString("questionnaireid");
        UUID questionnaireId = UUID.fromString(questionnaireIdStr);
        String userIdStr = resultSet.getString("userid");
        UUID userId = UUID.fromString(userIdStr);
        int score = resultSet.getInt("score");
        OffsetDateTime dateTime = resultSet.getObject("createdAt", OffsetDateTime.class);
        Dao<User> userDao = new UserDao();
        User user = userDao.get(userId);
        Dao<Questionnaire> questionnaireDao = new QuestionnaireDao();
        Questionnaire questionnaire = questionnaireDao.get(questionnaireId);
        DaoByParent<List<Turn>> daoByParent = new TurnDao();
        List<Turn> turnList = daoByParent.getByParent(quizId);
        return new StandardQuiz(user, turnList, questionnaire, quizId, dateTime, score);
    }
}
