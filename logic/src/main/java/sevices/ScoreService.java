package sevices;

import com.rijnders.entityinterfaces.Turn;

import java.util.List;

public class ScoreService implements ScoreServiceAble {

    @Override
    public int calcScore(List<Turn> turns) {
        double numberOfCorrectAnswers = (double) turns.stream().filter(t -> t.isCorrect()).count();
        double output = (numberOfCorrectAnswers / turns.size()) * 100;
        return (int) output;
    }
}
