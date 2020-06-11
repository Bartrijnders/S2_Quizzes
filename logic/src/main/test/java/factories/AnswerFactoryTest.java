package factories;

import com.rijnders.entities.StandardAnswer;
import com.rijnders.entityinterfaces.Answer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class AnswerFactoryTest {

    private AnswerFactoryAble toTest;

    @BeforeEach
    void setUp() {
        toTest = new AnswerFactory();
    }

    @Test
    void itShouldCreateANewAnswer() {
        //Given
        String answerline = "answerline";
        UUID questionId = UUID.randomUUID();
        boolean isCorrect = true;
        Answer expected = new StandardAnswer(answerline, isCorrect, questionId);
        //When
        Answer actual = toTest.create(answerline, questionId, isCorrect);
        //Then
        assertThat(actual.getAnswerLine()).isEqualTo(expected.getAnswerLine());
        assertThat(actual.getQuestionId()).isEqualTo(expected.getQuestionId());
        assertThat(actual.isCorrect()).isEqualTo(expected.isCorrect());
    }

    @Test
    void itShouldCreateAExistingAnswer() {
        //Given
        String answerline = "answerline";
        UUID questionId = UUID.randomUUID();
        UUID id = UUID.randomUUID();
        boolean isCorrect = true;
        Answer expected = new StandardAnswer(answerline, isCorrect, id, questionId);
        //When
        Answer actual = toTest.create(answerline, id, questionId, isCorrect);
        //Then
        assertThat(actual).isEqualToComparingFieldByField(expected);

    }
}