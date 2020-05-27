package sevices;

import com.rijnders.dao.DaoByParent;
import com.rijnders.dao.QuestionnaireDao;
import com.rijnders.entityinterfaces.Questionnair;
import com.rijnders.entityinterfaces.User;

import java.sql.SQLException;
import java.util.List;

public class QuestionnaireService {

    private final DaoByParent<List<Questionnair>> daoByParent;

    public QuestionnaireService() {
        this.daoByParent = new QuestionnaireDao();
    }

    public List<Questionnair> getAllUsersQuestionnaires(User user) throws SQLException {
        List<Questionnair> output;
        output = daoByParent.getByParent(user.getUserId());
        return output;
    }
}
