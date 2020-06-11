package sevices;

import com.rijnders.entities.StandardUser;
import com.rijnders.entityinterfaces.User;
import messages.RegistryCheckMessageAble;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.sql.SQLException;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

class RegisterInputServiceTest {

    private RegisterInputService toTest;
//    private final String password = "password";
//    private final String email = "test@email.com";
//    private final String userName = "test";
//    private UserDao userDao;
//    private User user;

    static Stream<Arguments> generateData() {
        User user = new StandardUser("test", "test@email.com", "test");
        return Stream.of(
                Arguments.of("test", "test@email.com", false, false, user, user),
                Arguments.of("fdjsaojdiofoeih", "hoahodiiofeo", true, true, null, null),
                Arguments.of("test", "ihoiioh", true, false, null, user),
                Arguments.of("hhouhoihjbjkbk", "test@email.com", false, true, user, null)
        );
    }

    @BeforeEach
    void setUp() throws SQLException {
        toTest = new RegisterInputService();
    }

    @ParameterizedTest
    @MethodSource("generateData")
    void itShouldCheckInput(String userName, String email, boolean exEmail, boolean exUserName, User userEmail, User userUserName) throws SQLException {
        //Given
        RegisterInputService spyService = Mockito.spy(toTest);
        given(spyService.getByEmail(email)).willReturn(userEmail);
        given(spyService.getByUsername(userName)).willReturn(userUserName);
        //When
        RegistryCheckMessageAble actual = spyService.checkInput(userName, email);
        //Then
        assertThat(actual.getEmailIsUnique()).isEqualTo(exEmail);
        assertThat(actual.getUsernameIsUnique()).isEqualTo(exUserName);


    }

    @AfterEach
    void tearDown() {
    }
}