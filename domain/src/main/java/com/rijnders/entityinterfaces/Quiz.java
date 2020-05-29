package com.rijnders.entityinterfaces;

import java.util.List;
import java.util.UUID;

public interface Quiz {
    User getParticipant();

    List<Turn> getTurns();

    Questionnaire getQuestionnair();

    UUID getId();
}
