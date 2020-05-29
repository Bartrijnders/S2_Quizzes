package com.rijnders.entities;

import com.rijnders.entityinterfaces.Questionnaire;
import com.rijnders.entityinterfaces.Quiz;
import com.rijnders.entityinterfaces.Turn;
import com.rijnders.entityinterfaces.User;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StandardQuiz implements Quiz {

    private final User participant;
    private final List<Turn> turns;
    private final Questionnaire questionnaire;
    private final UUID id;
    private final LocalTime timeOfCreation;


    public StandardQuiz(User participant, List<Turn> turns, Questionnaire questionnaire, UUID id, LocalTime timeOfCreation) {
        this.participant = participant;
        this.turns = turns;
        this.questionnaire = questionnaire;
        this.id = id;
        this.timeOfCreation = timeOfCreation;
    }

    public StandardQuiz(User participant, Questionnaire questionnaire) {
        this.participant = participant;
        this.questionnaire = questionnaire;
        this.id = UUID.randomUUID();
        this.turns = new ArrayList<>();
        this.timeOfCreation = LocalTime.now();
    }

    public LocalTime getTimeOfCreation() {
        return timeOfCreation;
    }

    @Override
    public User getParticipant() {
        return participant;
    }

    @Override
    public List<Turn> getTurns() {
        return turns;
    }

    @Override
    public Questionnaire getQuestionnair() {
        return questionnaire;
    }

    @Override
    public UUID getId() {
        return id;
    }
}
