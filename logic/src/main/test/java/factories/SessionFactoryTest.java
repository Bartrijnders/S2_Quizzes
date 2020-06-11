package factories;

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
import static org.mockito.BDDMockito.given;

class SessionFactoryTest {

    private SessionFactory toTest;
    @Mock
    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        toTest = new SessionFactory();
    }

    @Test
    void itShouldCreateASessionAbleObject() throws SQLException {
        //Given
        UUID userId = UUID.randomUUID();
        given(user.getUserId()).willReturn(userId);
        SessionAble expected = new QuizzesSession(user);
        //When
        SessionAble actual = toTest.create(user);
        //Then
        assertThat(actual.getActiveUserService().getUser().getUserId()).isEqualTo(actual.getActiveUserService().getUser().getUserId());
    }
}