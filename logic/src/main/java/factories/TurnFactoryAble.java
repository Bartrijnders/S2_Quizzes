package factories;

import com.rijnders.entities.StandardQuestion;
import com.rijnders.entityinterfaces.Turn;

import java.util.UUID;

public interface TurnFactoryAble {
    Turn create(int number, StandardQuestion question, UUID quizId);
}
