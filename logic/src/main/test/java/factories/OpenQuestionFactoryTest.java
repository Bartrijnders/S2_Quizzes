package factories;

import com.rijnders.entities.OpenQuestion;
import com.rijnders.entities.StandardAnswer;
import com.rijnders.entities.StandardQuestionnaire;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;


class OpenQuestionFactoryTest {
    //String questionLine, Questionnaire questionnaire, Answer answer, UUID uuid
    //String questionLine, Questionnaire questionnaire
    private OpenQuestionFactory toTest;
    @Mock
    private StandardQuestionnaire questionnaire;
    private String questionLine;
    private UUID uuid;
    @Mock
    private StandardAnswer answer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        toTest = new OpenQuestionFactory();
        questionLine = "questionLine";
        uuid = UUID.randomUUID();
    }

    @Test
    void itShouldCreateANEWOpenQuestion() {
        //Given
        UUID givenId = UUID.randomUUID();
        given(questionnaire.getId()).willReturn(givenId);
        OpenQuestion expected = new OpenQuestion(questionLine, questionnaire.getId());
        //When
        OpenQuestion actual = toTest.create(questionLine, questionnaire);
        //Then
        assertThat(actual.getQuestionLine()).isEqualTo(expected.getQuestionLine());
        assertThat(actual.getQuestionnaireId()).isEqualTo(expected.getQuestionnaireId());
    }

    @Test
    void itShouldCreateAExistingOpenQuestion() {
        //Given
        UUID givenId = UUID.randomUUID();
        given(questionnaire.getId()).willReturn(givenId);
        OpenQuestion expected = new OpenQuestion(questionLine, uuid, questionnaire.getId(), answer);
        //When
        OpenQuestion actual = toTest.create(questionLine, questionnaire, answer, uuid);
        //Then
        assertThat(actual).isEqualToComparingFieldByField(expected);

    }
}