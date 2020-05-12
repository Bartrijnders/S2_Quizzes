package sevices;

import com.rijnders.entityinterfaces.Questionnair;
import com.rijnders.entityinterfaces.User;
import com.rijnders.questionnaire.QuestionnaireDao;

import java.util.List;

public class QuestionnaireService {

    private QuestionnaireDao questionnaireDao;

    public QuestionnaireService() {
        this.questionnaireDao = new QuestionnaireDao();
    }

    public List<Questionnair> getAllUsersQuestionnaires(User user){
        List<Questionnair> output;
        output = questionnaireDao.selectAllUserQuestionnaire(user);
        return output;
    }
}
