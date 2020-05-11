package com.rijnders.questionnaire;

import com.rijnders.entityinterfaces.Questionnair;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class ResultToQuestinnaireConverter {

    public Questionnair convertToStandard(ResultSet resultSet) throws SQLException {
        String idStr = resultSet.getString("id");
        UUID id = UUID.fromString(idStr);
        String userIdStr = resultSet.getString("userid");
        UUID userId = UUID.fromString(userIdStr);
        String name = resultSet.getString("name");
        Questionnair output = null;
        return output;

    }
}
