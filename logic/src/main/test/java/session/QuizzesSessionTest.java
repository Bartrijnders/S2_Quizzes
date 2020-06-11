package session;

import com.rijnders.entityinterfaces.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import sevices.ActiveQuestionnairesService;
import sevices.ActiveUserService;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

class QuizzesSessionTest {

    private SessionAble toTest;

    @Mock
    private User user;

    @BeforeEach
    void setUp() throws SQLException {
        MockitoAnnotations.initMocks(this);
        toTest = new QuizzesSession(user);
    }

    @Test
    void itShouldGetActiveUserService() {
        //Given
        //When
        ActiveUserService actual = toTest.getActiveUserService();
        //Then
        assertThat(actual).isInstanceOf(ActiveUserService.class);

    }

    @Test
    void itShouldSetActiveUserService() {
        //Given
        ActiveUserService expected = new ActiveUserService();
        //When
        toTest.setActiveUserService(expected);
        //Then
        assertThat(toTest.getActiveUserService()).isEqualTo(expected);

    }

    @Test
    void itShouldGetActiveQuestionnaireService() {
        //Given
        //When
        QuestionnaireCollectionManageAble actual = toTest.getActiveQuestionnaireService();
        //Then
        assertThat(actual).isInstanceOf(ActiveQuestionnairesService.class);
    }

    @Test
    void itShouldSetActiveQuestionnaireService() {
        //Given
        QuestionnaireCollectionManageAble expected = new ActiveQuestionnairesService();
        //When
        toTest.setActiveQuestionnaireService(expected);
        //Then
        assertThat(toTest.getActiveQuestionnaireService()).isEqualTo(expected);

    }
}