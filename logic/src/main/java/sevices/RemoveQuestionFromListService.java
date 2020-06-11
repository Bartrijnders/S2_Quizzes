package sevices;

import com.rijnders.entities.StandardQuestion;

import java.util.List;

public class RemoveQuestionFromListService {
    public void removeQuestion(StandardQuestion question, List<StandardQuestion> questions) {
        questions.remove(question);
    }
}
