package factories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sevices.ActiveUserService;

import static org.assertj.core.api.Assertions.assertThat;

class ActiveUserServiceFactoryTest {

    private ActiveUserServiceFactory toTest;

    @BeforeEach
    void setUp() {
        toTest = new ActiveUserServiceFactory();
    }

    @Test
    void itShouldCreateAnActiveUserService() {
        //Given
        //When
        ActiveUserService actual = toTest.Create();
        //Then
        assertThat(actual).isInstanceOf(ActiveUserService.class);

    }
}