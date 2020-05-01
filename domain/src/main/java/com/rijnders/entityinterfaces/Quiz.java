package com.rijnders.entityinterfaces;

import java.util.List;
import java.util.UUID;

public interface Quiz {
    User getParticipant();

    List<Turn> getTurns();

    Questionnair getQuestionnair();

    UUID getId();
}
