package factories;

import com.rijnders.entities.MultipleChoiceQuestion;
import com.rijnders.entityinterfaces.Answer;

import java.util.List;
import java.util.UUID;

public interface MultipleChoiceQuestionFactoryAble {
    MultipleChoiceQuestion create(String questionLine, UUID questionnaireId, List<Answer> answers, UUID uuid);

    MultipleChoiceQuestion create(String questionLine, UUID questionnaireId);
}
