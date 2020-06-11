package com.rijnders.resultconvertors;

import com.rijnders.dao.AnswerDao;
import com.rijnders.entities.MultipleChoiceQuestion;
import com.rijnders.entities.OpenQuestion;
import com.rijnders.entities.QuestionType;
import com.rijnders.entities.StandardQuestion;
import com.rijnders.entityinterfaces.Answer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class ResultToQuestionConverter implements ResultConverter<StandardQuestion> {


    private static ResultToQuestionConverter resultToQuestionConverterInstance;


    private ResultToQuestionConverter() {
    }

    public static ResultToQuestionConverter getInstance() {
        if (resultToQuestionConverterInstance == null) {
            resultToQuestionConverterInstance = new ResultToQuestionConverter();
        }
        return resultToQuestionConverterInstance;
    }

    @Override
    public StandardQuestion convert(ResultSet resultSet) throws SQLException {
        @SuppressWarnings("SpellCheckingInspection") String questionIdStr = resultSet.getString("questionid");
        UUID questionId = UUID.fromString(questionIdStr);
        @SuppressWarnings("SpellCheckingInspection") String questionnaireIdStr = resultSet.getString("questionnaireid");
        UUID questionnaireId = UUID.fromString(questionnaireIdStr);
        @SuppressWarnings("SpellCheckingInspection") String questionLine = resultSet.getString("questionline");
        String questionTypeStr = resultSet.getString("type");
        QuestionType questionType = QuestionType.valueOf(questionTypeStr);
        AnswerDao answerDao = new AnswerDao();
        List<Answer> answers = answerDao.getByParent(questionId);
        switch (questionType){
            case OPEN:
                return new OpenQuestion(questionLine, questionId, questionnaireId ,answers.get(0));
            case MULTIPLECHOICE:
                return new MultipleChoiceQuestion(questionLine, questionId, questionnaireId, answers);
            default:
                return null;
        }
    }
}
