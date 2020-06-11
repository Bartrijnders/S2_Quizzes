package sevices;

import com.rijnders.dao.Dao;
import com.rijnders.dao.QuestionnaireDao;
import com.rijnders.entityinterfaces.Questionnaire;
import session.SessionAble;

import java.sql.SQLException;

public class QuestionnaireDeleteService implements DeleteService<Questionnaire, SessionAble> {

    private final Dao<Questionnaire> dao;

    public QuestionnaireDeleteService() throws SQLException {
        this.dao = new QuestionnaireDao();
    }

    @Override
    public void delete(Questionnaire questionnaire, SessionAble sessionAble) throws SQLException {
        dao.delete(questionnaire);
        sessionAble.getActiveQuestionnaireService().getQCollection().remove(questionnaire);
    }
}
