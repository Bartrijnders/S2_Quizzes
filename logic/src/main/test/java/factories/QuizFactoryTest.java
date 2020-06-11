package factories;

import com.rijnders.entities.StandardQuestionnaire;
import com.rijnders.entities.StandardUser;
import com.rijnders.entityinterfaces.Questionnaire;
import com.rijnders.entityinterfaces.Quiz;
import com.rijnders.entityinterfaces.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class QuizFactoryTest {

    private QuizFactoryAble toTest;
    private User user;
    private Questionnaire questionnaire;

    @BeforeEach
    void setUp() {
        toTest = new QuizFactory();
        user = new StandardUser("test", "test", "test");
        questionnaire = new StandardQuestionnaire("test", user);
    }

    @Test
    void itShouldName() {
        //Given
        //When
        Quiz actual = toTest.Create(user, questionnaire);
        //Then
        assertThat(actual).isInstanceOf(Quiz.class);

    }
}
