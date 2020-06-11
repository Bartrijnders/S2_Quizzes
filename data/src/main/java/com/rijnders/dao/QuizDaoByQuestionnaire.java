package com.rijnders.dao;

import com.rijnders.entityinterfaces.Quiz;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface QuizDaoByQuestionnaire {
    List<Quiz> getByQuestionnaire(UUID questionnaireId) throws SQLException;
}
