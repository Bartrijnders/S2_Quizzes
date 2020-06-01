package session;

import factories.ActiveQuestionnaireServiceFactory;
import factories.ActiveUserServiceFactory;
import factories.Factory;
import factories.QuestionnaireSeviceFactory;
import sevices.ActiveUserService;
import sevices.QuestionnaireService;

public class QuizzesSession implements SessionAble {

    private ActiveUserService activeUserService;
    private QuestionnaireCollectionManageAble activeQuestionnaireCollectionService;
    private QuestionnaireService questionnaireService;

    public QuizzesSession() {
        Factory<QuestionnaireCollectionManageAble> qCollectionFactory = new ActiveQuestionnaireServiceFactory();
        activeQuestionnaireCollectionService = qCollectionFactory.Create();
        Factory<ActiveUserService> userServiceFactory = new ActiveUserServiceFactory();
        activeUserService = userServiceFactory.Create();
        Factory<QuestionnaireService> questionnaireServiceFactory = new QuestionnaireSeviceFactory();
        questionnaireService = questionnaireServiceFactory.Create();

    }

    @Override
    public ActiveUserService getActiveUserService() {

        return activeUserService;
    }

    @Override
    public void setActiveUserService(ActiveUserService activeUserService) {
        this.activeUserService = activeUserService;
    }

    @Override
    public QuestionnaireCollectionManageAble getActiveQuestionnaireService() {
        return activeQuestionnaireCollectionService;
    }

    @Override
    public void setActiveQuestionnaireService(QuestionnaireCollectionManageAble questionnaireService) {
        this.activeQuestionnaireCollectionService = questionnaireService;
    }

    @Override
    public QuestionnaireService getQuestionnaireService() {
        return questionnaireService;
    }

    @Override
    public void setQuestionnaireService(QuestionnaireService questionnaireService) {
        this.questionnaireService = questionnaireService;
    }
}
