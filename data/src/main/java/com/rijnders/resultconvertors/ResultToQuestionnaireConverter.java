package com.rijnders.resultconvertors;

import com.rijnders.dao.Dao;
import com.rijnders.dao.DaoByParent;
import com.rijnders.dao.QuestionDao;
import com.rijnders.dao.UserDao;
import com.rijnders.entities.StandardQuestion;
import com.rijnders.entities.StandardQuestionnaire;
import com.rijnders.entityinterfaces.Questionnaire;
import com.rijnders.entityinterfaces.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class ResultToQuestionnaireConverter {


    private static ResultToQuestionnaireConverter resultToQuestionnaireConverter;

    private ResultToQuestionnaireConverter() {
    }

    public static ResultToQuestionnaireConverter getInstance() {
        if (resultToQuestionnaireConverter == null) {
            resultToQuestionnaireConverter = new ResultToQuestionnaireConverter();
        }
        return resultToQuestionnaireConverter;
    }

    public Questionnaire convertToStandard(ResultSet resultSet) throws SQLException {
        @SuppressWarnings("SpellCheckingInspection") String idStr = resultSet.getString("questionnaireid");
        UUID id = UUID.fromString(idStr);
        @SuppressWarnings("SpellCheckingInspection") String userIdStr = resultSet.getString("userid");
        UUID userId = UUID.fromString(userIdStr);
        String name = resultSet.getString("name");
        DaoByParent<List<StandardQuestion>> daoByParent = new QuestionDao();
        List<StandardQuestion> questions = daoByParent.getByParent(id);
        Dao<User> dao = new UserDao();
        return new StandardQuestionnaire(id, name, questions, dao.get(userId));
    }
}
