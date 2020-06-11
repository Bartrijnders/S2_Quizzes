package com.rijnders.entityinterfaces;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public interface Quiz {
    User getParticipant();

    List<Turn> getTurns();

    Questionnaire getQuestionnaire();

    UUID getId();

    int getScore();

    void setScore(int score);

    OffsetDateTime getTime();
}
