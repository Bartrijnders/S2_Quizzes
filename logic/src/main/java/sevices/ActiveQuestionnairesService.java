package sevices;

import com.rijnders.entityinterfaces.Questionnaire;
import session.QuestionnaireCollectionManageAble;

import java.util.ArrayList;
import java.util.List;

public class ActiveQuestionnairesService implements QuestionnaireCollectionManageAble {

    private List<Questionnaire> questionnaires;

    public ActiveQuestionnairesService() {
        this.questionnaires = new ArrayList<>();
    }

    @Override
    public List<Questionnaire> getQCollection() {
        return questionnaires;
    }

    @Override
    public void setQCollection(List<Questionnaire> qCollection) {
        this.questionnaires = qCollection;
    }
}
