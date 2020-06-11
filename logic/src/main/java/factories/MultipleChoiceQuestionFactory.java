package factories;

import com.rijnders.entities.MultipleChoiceQuestion;
import com.rijnders.entityinterfaces.Answer;

import java.util.List;
import java.util.UUID;

public class MultipleChoiceQuestionFactory implements MultipleChoiceQuestionFactoryAble {

    @Override
    public MultipleChoiceQuestion create(String questionLine, UUID questionnaireId, List<Answer> answers, UUID uuid) {
        return new MultipleChoiceQuestion(questionLine, uuid, questionnaireId, answers);
    }

    @Override
    public MultipleChoiceQuestion create(String questionLine, UUID questionnaireId) {
        return new MultipleChoiceQuestion(questionLine, questionnaireId);
    }
}
