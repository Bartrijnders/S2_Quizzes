package sevices;

import com.rijnders.dao.Dao;
import com.rijnders.dao.QuestionnaireDao;
import com.rijnders.entityinterfaces.Questionnaire;
import session.SessionAble;

import java.sql.SQLException;

public class QuestionnaireSaveService implements SaveService<Questionnaire, SessionAble> {

    private final Dao<Questionnaire> dao;

    public QuestionnaireSaveService() throws SQLException {
        this.dao = new QuestionnaireDao();
    }

    @Override
    public void save(Questionnaire toSave, SessionAble sessionAble) throws SQLException {
        dao.save(toSave);
        sessionAble.getActiveQuestionnaireService().getQCollection().add(toSave);
    }
}
