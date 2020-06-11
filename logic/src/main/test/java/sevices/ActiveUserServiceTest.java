package sevices;

import com.rijnders.entities.StandardUser;
import com.rijnders.entityinterfaces.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class ActiveUserServiceTest {

    ActiveUserService toTest;

    @BeforeEach
    void setUp() {
        toTest = new ActiveUserService();
    }

    @Test
    void itShouldGetUser() {
        //Given
        User expected = new StandardUser(UUID.randomUUID(), "test", "test@test.com", "password");
        toTest.setUser(expected);
        //When
        User actual = toTest.getUser();
        //Then
        assertThat(actual).isEqualTo(expected);

    }

    @Test
    void itShouldSetUser() {
        //Given
        User user = new StandardUser(UUID.randomUUID(), "test", "test@test.com", "password");
        //When
        toTest.setUser(user);
        //Then
        assertThat(toTest.getUser().getUserId()).isEqualTo(user.getUserId());
        assertThat(toTest.getUser().getEmail()).isEqualTo(user.getEmail());
        assertThat(toTest.getUser().getPassword()).isEqualTo(user.getPassword());
        assertThat(toTest.getUser().getUserName()).isEqualTo(user.getUserName());
    }
}