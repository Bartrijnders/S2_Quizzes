package factories;

import com.rijnders.entityinterfaces.Questionnaire;
import com.rijnders.entityinterfaces.Quiz;
import com.rijnders.entityinterfaces.User;

public interface QuizFactoryAble {
    Quiz Create(User user, Questionnaire questionnaire);
}
