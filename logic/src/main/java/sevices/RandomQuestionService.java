package sevices;

import com.rijnders.entities.StandardQuestion;

import java.util.List;

public class RandomQuestionService implements RandomQuestionServiceAble {

    @Override
    public StandardQuestion getRandomQuestionFromList(List<StandardQuestion> questions) {
        RandomNumberService randomNumberService = new RandomNumberService();
        int index = randomNumberService.getRandomInt(questions.size());
        return questions.get(index);
    }
}
