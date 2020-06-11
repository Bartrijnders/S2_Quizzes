package factories;

import com.rijnders.entities.StandardQuiz;
import com.rijnders.entityinterfaces.Questionnaire;
import com.rijnders.entityinterfaces.Quiz;
import com.rijnders.entityinterfaces.User;

public class QuizFactory implements QuizFactoryAble {


    public Quiz Create(User user, Questionnaire questionnaire) {
        return new StandardQuiz(user, questionnaire);
    }
}
