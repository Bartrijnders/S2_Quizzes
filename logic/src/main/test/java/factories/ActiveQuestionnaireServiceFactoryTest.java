package factories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import session.QuestionnaireCollectionManageAble;
import sevices.ActiveQuestionnairesService;

import static org.assertj.core.api.Assertions.assertThat;

class ActiveQuestionnaireServiceFactoryTest {

    private ActiveQuestionnaireServiceFactory toTest;

    @BeforeEach
    void setUp() {
        toTest = new ActiveQuestionnaireServiceFactory();
    }

    @Test
    void itShouldCreateNewActiveQuestionnairesService() {
        //Given
        //When

        QuestionnaireCollectionManageAble activeQuestionnairesService = toTest.Create();
        //Then
        assertThat(activeQuestionnairesService).isInstanceOf(ActiveQuestionnairesService.class);

    }
}