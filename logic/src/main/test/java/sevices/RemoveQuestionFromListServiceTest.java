package sevices;

import com.rijnders.entities.MultipleChoiceQuestion;
import com.rijnders.entities.OpenQuestion;
import com.rijnders.entities.StandardQuestion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class RemoveQuestionFromListServiceTest {

    private RemoveQuestionFromListService toTest;
    private List<StandardQuestion> questions;
    private StandardQuestion question1;
    private StandardQuestion question2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        toTest = new RemoveQuestionFromListService();
        question1 = new OpenQuestion("test1", UUID.randomUUID());
        question2 = new MultipleChoiceQuestion("test2", UUID.randomUUID());
        questions = new ArrayList<>();
        questions.add(question1);
        questions.add(question2);
    }

    @Test
    void itShouldName() {
        //Given
        //When
        toTest.removeQuestion(question1, questions);
        //Then
        assertThat(questions).doesNotContain(question1);

    }
}
