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

public class ResultToQuestinnaireConverter {


    private static ResultToQuestinnaireConverter resultToQuestinnaireConverterInstance;

    private ResultToQuestinnaireConverter() {
    }

    public static ResultToQuestinnaireConverter getInstance() {
        if (resultToQuestinnaireConverterInstance == null) {
            resultToQuestinnaireConverterInstance = new ResultToQuestinnaireConverter();
        }
        return resultToQuestinnaireConverterInstance;
    }

    public Questionnaire convertToStandard(ResultSet resultSet) throws SQLException {
        String idStr = resultSet.getString("questionnaireid");
        UUID id = UUID.fromString(idStr);
        String userIdStr = resultSet.getString("userid");
        UUID userId = UUID.fromString(userIdStr);
        String name = resultSet.getString("name");
        DaoByParent<List<StandardQuestion>> daoByParent = new QuestionDao();
        List<StandardQuestion> questions = daoByParent.getByParent(id);
        Dao<User> dao = new UserDao();
        return new StandardQuestionnaire(id, name, questions, dao.get(userId));
    }
}
