package sevices;

import com.rijnders.entities.StandardQuestion;

import java.util.List;

public interface RandomQuestionServiceAble {
    StandardQuestion getRandomQuestionFromList(List<StandardQuestion> questions);
}
