package factories;

import com.rijnders.entities.StandardQuestion;
import com.rijnders.entities.StandardTurn;
import com.rijnders.entityinterfaces.Turn;

import java.util.UUID;

public class TurnFactory implements TurnFactoryAble {

    @Override
    public Turn create(int number, StandardQuestion question, UUID quizid) {
        return new StandardTurn(number, question, quizid);
    }
}
