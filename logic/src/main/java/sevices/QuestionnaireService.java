package sevices;

import com.rijnders.dao.Dao;
import com.rijnders.dao.DaoByParent;
import com.rijnders.dao.QuestionnaireDao;
import com.rijnders.entityinterfaces.Questionnaire;
import com.rijnders.entityinterfaces.User;

import java.sql.SQLException;
import java.util.List;

public class QuestionnaireService {

    private final DaoByParent<List<Questionnaire>> daoByParent;
    private final Dao<Questionnaire> dao;

    public QuestionnaireService() {
        this.daoByParent = new QuestionnaireDao();
        this.dao = new QuestionnaireDao();
    }

    public List<Questionnaire> getAllUsersQuestionnaires(User user) throws SQLException {
        List<Questionnaire> output = null;
        output = daoByParent.getByParent(user.getUserId());
        return output;
    }

    public void save(Questionnaire questionnaire) throws SQLException {
        dao.save(questionnaire);
    }
}
