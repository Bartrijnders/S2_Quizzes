package session;

import com.rijnders.entityinterfaces.Questionnaire;
import com.rijnders.entityinterfaces.User;
import factories.ActiveQuestionnaireServiceFactory;
import factories.ActiveUserServiceFactory;
import factories.Factory;
import sevices.ActiveUserService;
import sevices.GetByValueService;
import sevices.QuestionnaireGetAllByUserService;

import java.sql.SQLException;
import java.util.List;

public class QuizzesSession implements SessionAble {

    private ActiveUserService activeUserService;
    private QuestionnaireCollectionManageAble activeQuestionnaireCollectionService;

    public QuizzesSession(User user) throws SQLException {
        Factory<QuestionnaireCollectionManageAble> qCollectionFactory = new ActiveQuestionnaireServiceFactory();
        activeQuestionnaireCollectionService = qCollectionFactory.Create();
        Factory<ActiveUserService> userServiceFactory = new ActiveUserServiceFactory();
        activeUserService = userServiceFactory.Create();
        GetByValueService<User, List<Questionnaire>> getByValueService = new QuestionnaireGetAllByUserService();
        activeUserService.setUser(user);
        getActiveQuestionnaireService().setQCollection(getByValueService.getByValue(user));
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

}
