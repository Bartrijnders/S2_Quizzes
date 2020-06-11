package sevices;

import com.rijnders.entityinterfaces.Questionnaire;

import java.util.List;

public class QuestionnaireValueChecker implements ValueChecker<String, List<Questionnaire>> {


    @Override
    public boolean checkForName(String name, List<Questionnaire> collection) {
        //returns true if the questionnaire name is unique else it will return false.
        Questionnaire questionnaire = collection.stream()
                .filter(questionnaire1 -> questionnaire1.getName().equals(name))
                .findFirst().orElse(null);
        return questionnaire == null;

    }
}
