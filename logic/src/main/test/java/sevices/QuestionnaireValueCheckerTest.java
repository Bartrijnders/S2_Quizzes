package sevices;

import com.rijnders.entities.StandardQuestionnaire;
import com.rijnders.entityinterfaces.Questionnaire;
import com.rijnders.entityinterfaces.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class QuestionnaireValueCheckerTest {

    private QuestionnaireValueChecker toTest;
    @Mock
    private User user;
    private List<Questionnaire> questionnaires;

    private static Stream<Arguments> generateData() {
        return Stream.of(
                Arguments.of("test", false),
                Arguments.of("fail", true)
        );
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        toTest = new QuestionnaireValueChecker();
        Questionnaire questionnaire = new StandardQuestionnaire("test", user);
        questionnaires = new ArrayList<>();
        questionnaires.add(questionnaire);
    }

    @AfterEach
    void tearDown() {
    }


    @ParameterizedTest
    @MethodSource("generateData")
    void itShouldCheckForName(String name, boolean expected) {
        //Given
        //When
        boolean actual = toTest.checkForName(name, questionnaires);
        //Then
        assertThat(actual).isEqualTo(expected);
    }
}