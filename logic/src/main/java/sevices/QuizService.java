package sevices;

import com.rijnders.dao.QuizDao;
import com.rijnders.dao.QuizDaoByQuestionnaire;
import com.rijnders.entityinterfaces.Quiz;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class QuizService implements QuizServiceAble {

    @Override
    public List<Quiz> getQuizzesFromQuestionnaire(UUID questionnaireId) throws SQLException {
        QuizDaoByQuestionnaire daoByQuestionnaire = new QuizDao();
        return daoByQuestionnaire.getByQuestionnaire(questionnaireId);
    }
}
