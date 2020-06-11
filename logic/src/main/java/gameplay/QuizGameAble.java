package gameplay;

import com.rijnders.entityinterfaces.Quiz;
import com.rijnders.entityinterfaces.Turn;

import java.sql.SQLException;

public interface QuizGameAble {
    Turn getActiveTurn();

    void getNextTurn() throws SQLException;

    boolean answerOpenQuestion(String chosenAnswer) throws SQLException;

    boolean answerMultipleChoiceQuestion(String chosenAnswer) throws SQLException;

    Quiz getQuiz();
}
