package sevices;

import com.rijnders.dao.Dao;
import com.rijnders.dao.QuestionnaireDao;
import com.rijnders.entities.StandardQuestionnaire;
import com.rijnders.entityinterfaces.Questionnaire;
import com.rijnders.entityinterfaces.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import session.QuizzesSession;
import session.SessionAble;

import java.sql.SQLException;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class QuestionnaireDeleteServiceTest {

    private QuestionnaireDeleteService toTest;

    private Questionnaire questionnaire;

    @Mock
    private User user;

    private SessionAble session;

    private Dao<Questionnaire> questionnaireDao;

    private UUID id;

    @BeforeEach
    void setUp() throws SQLException {
        toTest = new QuestionnaireDeleteService();
        MockitoAnnotations.initMocks(this);
        questionnaire = new StandardQuestionnaire("test", user);
        session = new QuizzesSession(user);
        session.getActiveQuestionnaireService().getQCollection().add(questionnaire);
        questionnaireDao = new QuestionnaireDao();
        id = questionnaire.getId();
    }

    @Test
    void itShouldDelete() throws SQLException {
        //Given
        //When
        toTest.delete(questionnaire, session);
        //Then
        Questionnaire q = questionnaireDao.get(id);
        assertThat(q).isNull();
        assertThat(session.getActiveQuestionnaireService().getQCollection()).isEmpty();

    }
}