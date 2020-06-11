package factories;

import com.rijnders.entities.MultipleChoiceQuestion;
import com.rijnders.entityinterfaces.Answer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class MultipleChoiceQuestionFactoryTest {

    private MultipleChoiceQuestionFactory toTest;
    @Mock
    private ArrayList<Answer> answerList;
    private UUID id;
    private String questionLine;
    private UUID questionnaireId;

    @BeforeEach
    void setUp() {
        answerList = new ArrayList<>();
        toTest = new MultipleChoiceQuestionFactory();
        id = UUID.randomUUID();
        questionLine = "questionLine";
        questionnaireId = UUID.randomUUID();
    }

    //String questionLine, UUID questionnaireId, List<Answer> answers, UUID uuid
    //
    @Test
    void itShouldCreateANewMultipleChoiceQuestion() {
        //Given
        MultipleChoiceQuestion expected = new MultipleChoiceQuestion(questionLine, questionnaireId);
        //When
        MultipleChoiceQuestion actual = toTest.create(questionLine, questionnaireId);
        //Then
        assertThat(actual.getQuestionnaireId()).isEqualTo(expected.getQuestionnaireId());
        assertThat(actual.getQuestionLine()).isEqualTo(expected.getQuestionLine());
    }

    @Test
    void itShouldCreateAnExistingMultipleChoiceQuestion() {
        //Given
        MultipleChoiceQuestion expected = new MultipleChoiceQuestion(questionLine, id, questionnaireId, answerList);
        //When
        MultipleChoiceQuestion actual = toTest.create(questionLine, questionnaireId, answerList, id);
        //Then
        assertThat(actual).isEqualToComparingFieldByField(expected);

    }
}