package factories;

import com.rijnders.entities.OpenQuestion;
import com.rijnders.entityinterfaces.Answer;
import com.rijnders.entityinterfaces.Questionnaire;

import java.util.UUID;

public class OpenQuestionFactory implements OpenQuestionFactoryAble {

    @Override
    public OpenQuestion create(String questionLine, Questionnaire questionnaire, Answer answer, UUID uuid) {
        return new OpenQuestion(questionLine, uuid, questionnaire.getId(), answer);
    }

    @Override
    public OpenQuestion create(String questionLine, Questionnaire questionnaire) {
        return new OpenQuestion(questionLine, questionnaire.getId());
    }
}
