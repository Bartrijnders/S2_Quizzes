package sevices;

import com.rijnders.entityinterfaces.Turn;

import java.util.List;

public interface ScoreServiceAble {
    int calcScore(List<Turn> turns);
}
