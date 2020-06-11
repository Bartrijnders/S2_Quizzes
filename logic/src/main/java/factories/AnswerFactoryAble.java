package factories;

import com.rijnders.entityinterfaces.Answer;

import java.util.UUID;

public interface AnswerFactoryAble {

    Answer create(String answerLine, UUID id, UUID questionId, boolean isCorrect);

    Answer create(String answerLine, UUID questionId, boolean isCorrect);


}
