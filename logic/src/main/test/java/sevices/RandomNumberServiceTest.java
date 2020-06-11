package sevices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class RandomNumberServiceTest {

    private RandomNumberService toTest;

    private static Stream<Arguments> generateData() {
        return Stream.of(
                Arguments.of(20),
                Arguments.of(400),
                Arguments.of(0),
                Arguments.of(1)

        );
    }

    @BeforeEach
    void setUp() {
        toTest = new RandomNumberService();
    }

    @ParameterizedTest
    @MethodSource("generateData")
    void itShouldReturnARandomNumberBetweenTwoNumbers(int max) {
        //Given
        //When
        int actual = toTest.getRandomInt(max);
        //Then
        assertThat(actual).isInstanceOf(Integer.class);
        if (max > 0) {
            assertThat(actual).isLessThan(max);
        }
        assertThat(actual).isGreaterThan(-1);


    }
}
