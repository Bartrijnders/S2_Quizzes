package factories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sevices.QuestionnaireGetAllByUserService;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

class QuestionnaireServiceFactoryTest {

    private QuestionnaireServiceFactory toTest;

    @BeforeEach
    void setUp() {
        toTest = new QuestionnaireServiceFactory();
    }

    @Test
    void itShouldCreateAQuestionnaireGetAllByUserService() throws SQLException {
        //Given
        //When
        QuestionnaireGetAllByUserService actual = toTest.Create();
        //Then
        assertThat(actual).isInstanceOf(QuestionnaireGetAllByUserService.class);

    }
}