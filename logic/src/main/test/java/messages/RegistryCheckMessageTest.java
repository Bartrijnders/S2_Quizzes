package messages;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RegistryCheckMessageTest {

    private RegistryCheckMessage toTest;

    @BeforeEach
    void setUp() {
        toTest = new RegistryCheckMessage(true, true);
    }

    @Test
    void itShouldGetUsernameIsUnique() {
        //Given
        //When
        //Then
        assertThat(toTest.getUsernameIsUnique()).isEqualTo(true);

    }

    @Test
    void itShouldGetEmailIsUnique() {
        //Given
        //When
        //Then
        assertThat(toTest.getEmailIsUnique()).isEqualTo(true);

    }
}