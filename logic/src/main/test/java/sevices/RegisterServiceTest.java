package sevices;

import com.rijnders.dao.UserDao;
import com.rijnders.entities.StandardUser;
import com.rijnders.entityinterfaces.User;
import messages.RegistryCheckMessage;
import messages.RegistryCheckMessageAble;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.sql.SQLException;
import java.util.UUID;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

class RegisterServiceTest {


    private final String password = "password";
    private final String email = "test@email.com";
    private final String userName = "test";
    private RegisterService toTest;
    private UserDao userDao;
    private User user;

    static Stream<Arguments> generateData() {
        return Stream.of(
                Arguments.of("test", "test@email.com", false, false),
                Arguments.of("fdjsaojdiofoeih", "hoahodiiofeo", true, true),
                Arguments.of("test", "ihoiioh", false, true),
                Arguments.of("hhouhoihjbjkbk", "test@email.com", true, false)
        );
    }

    @BeforeEach
    void setUp() throws SQLException {
        toTest = new RegisterService();
        userDao = new UserDao();
        user = new StandardUser(UUID.randomUUID(), userName, email, password);
        userDao.save(user);

    }

    @ParameterizedTest
    @MethodSource("generateData")
    void itShouldRegister(String userName, String email, boolean expectedUsername, boolean expectedEmail) throws SQLException {
        //Given
        RegisterService spyService = Mockito.spy(toTest);
        RegistryCheckMessage regmes = new RegistryCheckMessage(expectedUsername, expectedEmail);
        given(spyService.getCheck(userName, email)).willReturn(regmes);
        //When
        RegistryCheckMessageAble actual = toTest.register(userName, email, password);
        //Then
        assertThat(actual.getEmailIsUnique()).isEqualTo(expectedEmail);
        assertThat(actual.getUsernameIsUnique()).isEqualTo(expectedUsername);


    }

    @AfterEach
    void tearDown() throws SQLException {
        userDao = new UserDao();
        userDao.delete(user);
        User user = userDao.getByEmail("hoahodiiofeo");
        if (user != null) {
            userDao.delete(user);
        }
    }
}