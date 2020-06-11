package factories;

import com.rijnders.entities.OpenQuestion;
import com.rijnders.entityinterfaces.Answer;
import com.rijnders.entityinterfaces.Questionnaire;

import java.util.UUID;

public interface OpenQuestionFactoryAble {
    OpenQuestion create(String questionLine, Questionnaire questionnaire, Answer answer, UUID uuid);

    OpenQuestion create(String questionLine, Questionnaire questionnaire);
}
