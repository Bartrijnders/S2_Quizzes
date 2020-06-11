package sevices;

import com.rijnders.entityinterfaces.Quiz;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface QuizServiceAble {
    List<Quiz> getQuizzesFromQuestionnaire(UUID questionnaireId) throws SQLException;
}
