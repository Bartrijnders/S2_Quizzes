package factories;

import com.rijnders.entities.StandardAnswer;
import com.rijnders.entityinterfaces.Answer;

import java.util.UUID;

public class AnswerFactory implements AnswerFactoryAble {
    @Override
    public Answer create(String answerLine, UUID id, UUID questionId, boolean isCorrect) {
        return new StandardAnswer(answerLine, isCorrect, id, questionId);
    }

    @Override
    public Answer create(String answerLine, UUID questionId, boolean isCorrect) {
        return new StandardAnswer(answerLine, isCorrect, questionId);
    }
}
