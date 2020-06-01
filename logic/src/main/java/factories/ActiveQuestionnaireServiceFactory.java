package factories;

import session.QuestionnaireCollectionManageAble;
import sevices.ActiveQuestionnairesService;

public class ActiveQuestionnaireServiceFactory implements Factory<QuestionnaireCollectionManageAble> {

    @Override
    public QuestionnaireCollectionManageAble Create() {
        return new ActiveQuestionnairesService();
    }
}
