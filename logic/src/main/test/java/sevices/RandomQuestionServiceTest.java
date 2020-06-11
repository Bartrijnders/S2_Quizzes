package sevices;

import com.rijnders.entities.MultipleChoiceQuestion;
import com.rijnders.entities.OpenQuestion;
import com.rijnders.entities.StandardQuestion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class RandomQuestionServiceTest {

    private RandomQuestionServiceAble toTest;
    private List<StandardQuestion> questionnaire;
    private StandardQuestion question1;
    private StandardQuestion question2;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        toTest = new RandomQuestionService();
        question1 = new OpenQuestion("test", UUID.randomUUID());
        question2 = new MultipleChoiceQuestion("test", UUID.randomUUID());
        questionnaire.add(question1);
        questionnaire.add(question2);

    }

    @Test
    void itShouldName() {
        //Given
        //When
        StandardQuestion actual = toTest.getRandomQuestionFromList(questionnaire);
        //Then
        assertThat(actual).isInstanceOf(StandardQuestion.class);

    }
}
