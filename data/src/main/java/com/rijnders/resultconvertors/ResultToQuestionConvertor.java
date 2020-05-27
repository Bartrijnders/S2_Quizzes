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

public class ResultToQuestionConvertor implements ResultConvertor<StandardQuestion>{


    private static ResultToQuestionConvertor resultToQuestionConvertorInstance;


    private ResultToQuestionConvertor() {
    }

    public static ResultToQuestionConvertor getInstance() {
        if (resultToQuestionConvertorInstance == null) {
            resultToQuestionConvertorInstance = new ResultToQuestionConvertor();
        }
        return resultToQuestionConvertorInstance;
    }

    @Override
    public StandardQuestion convert(ResultSet resultSet) throws SQLException {
        String questionIdStr = resultSet.getString("questionid");
        UUID questionId = UUID.fromString(questionIdStr);
        String questionnaireIdStr = resultSet.getString("questionnaireid");
        UUID questionnaireId = UUID.fromString(questionnaireIdStr);
        String questionLine = resultSet.getString("questoinline");
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
