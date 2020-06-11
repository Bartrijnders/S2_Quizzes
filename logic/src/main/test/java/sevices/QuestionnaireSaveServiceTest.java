package sevices;

import com.rijnders.dao.Dao;
import com.rijnders.dao.QuestionnaireDao;
import com.rijnders.dao.UserDao;
import com.rijnders.entities.StandardQuestionnaire;
import com.rijnders.entities.StandardUser;
import com.rijnders.entityinterfaces.Questionnaire;
import com.rijnders.entityinterfaces.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import session.QuizzesSession;
import session.SessionAble;

import java.sql.SQLException;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class QuestionnaireSaveServiceTest {

    private QuestionnaireSaveService toTest;
    private Dao<Questionnaire> dao;
    private SessionAble session;
    private User user;
    private Questionnaire questionnaire;
    private UUID id;

    @BeforeEach
    void setUp() throws SQLException {
        user = new StandardUser("test", "test", "test");
        UserSaveService userSaveService = new UserSaveService();
        userSaveService.saveUser(user);
        toTest = new QuestionnaireSaveService();
        dao = new QuestionnaireDao();
        session = new QuizzesSession(user);
        questionnaire = new StandardQuestionnaire("test", user);
        id = questionnaire.getId();


    }

    @AfterEach
    void tearDown() throws SQLException {
        Questionnaire q = dao.get(id);
        if (q != null) {
            dao.delete(q);
        }
        UserDao userDao = new UserDao();
        userDao.delete(user);
    }

    @Test
    void itShouldSave() throws SQLException {
        //Given
        //When
        toTest.save(questionnaire, session);
        //Then
        Questionnaire actual = dao.get(id);
        assertThat(actual.getId()).isEqualTo(questionnaire.getId());
        assertThat(actual.getName()).isEqualTo(questionnaire.getName());
        assertThat(actual.getQuestions()).isEqualTo(questionnaire.getQuestions());
    }
}