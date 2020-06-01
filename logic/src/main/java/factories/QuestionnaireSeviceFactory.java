package factories;

import sevices.QuestionnaireService;

public class QuestionnaireSeviceFactory implements Factory<QuestionnaireService> {
    @Override
    public QuestionnaireService Create() {
        return new QuestionnaireService();
    }
}
