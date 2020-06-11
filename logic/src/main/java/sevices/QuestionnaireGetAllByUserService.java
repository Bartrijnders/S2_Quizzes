package sevices;

import com.rijnders.dao.DaoByParent;
import com.rijnders.dao.QuestionnaireDao;
import com.rijnders.entityinterfaces.Questionnaire;
import com.rijnders.entityinterfaces.User;

import java.sql.SQLException;
import java.util.List;

public class QuestionnaireGetAllByUserService implements GetByValueService<User, List<Questionnaire>> {

    private final DaoByParent<List<Questionnaire>> daoByParent;

    public QuestionnaireGetAllByUserService() throws SQLException {
        this.daoByParent = new QuestionnaireDao();
    }

    @Override
    public List<Questionnaire> getByValue(User user) throws SQLException {
        return daoByParent.getByParent(user.getUserId());
    }


}
