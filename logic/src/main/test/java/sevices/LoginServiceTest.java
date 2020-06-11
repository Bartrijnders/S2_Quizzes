package sevices;

import com.rijnders.dao.UserDao;
import com.rijnders.entities.StandardUser;
import com.rijnders.entityinterfaces.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class LoginServiceTest {

    private final String password = "password";
    private final String email = "test@email.com";
    private final String userName = "test";
    private LoginService toTest;
    private UserDao userDao;
    private User user;

    @BeforeEach
    void setUp() throws SQLException {
        toTest = new LoginService();
        userDao = new UserDao();
        user = new StandardUser(UUID.randomUUID(), userName, email, password);
        userDao.save(user);
    }

    @Test
    void itShouldLoginWithEmail() throws SQLException {
        //Given
        //When
        User actual = toTest.loginWithEmail(email, password);
        //Then
        assertThat(actual).isEqualToComparingFieldByField(user);
    }

    @Test
    void itShouldNotLogin() throws SQLException {
        //Given
        //When
        User actual = toTest.loginWithEmail("test", "test");
        //Then
        assertThat(actual).isNull();

    }

    @AfterEach
    void tearDown() throws SQLException {
        UserDao userDao = new UserDao();
        userDao.delete(user);
    }
}