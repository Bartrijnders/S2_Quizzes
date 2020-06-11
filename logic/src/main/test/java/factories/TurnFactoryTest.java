package factories;

import com.rijnders.entities.OpenQuestion;
import com.rijnders.entities.StandardQuestion;
import com.rijnders.entityinterfaces.Turn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class TurnFactoryTest {

    private TurnFactoryAble toTest;
    private StandardQuestion question;


    @BeforeEach
    void setUp() {
        toTest = new TurnFactory();
        question = new OpenQuestion("test", UUID.randomUUID());
    }

    @Test
    void itShouldCreateANewTurn() {
        //Given
        //When
        Turn actual = toTest.create(1, question, UUID.randomUUID());
        //Then
        assertThat(actual).isInstanceOf(Turn.class);
        assertThat(actual.getNumber()).isEqualTo(1);
        assertThat(actual.getQuestion()).isEqualTo(question);
    }
}




