package com.rijnders.entities;

import com.rijnders.entityinterfaces.Questionnair;
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
    private final Questionnair questionnair;
    private final UUID id;
    private LocalTime timeOfCreation;


    public StandardQuiz(User participant, List<Turn> turns, Questionnair questionnair, UUID id, LocalTime timeOfCreation) {
        this.participant = participant;
        this.turns = turns;
        this.questionnair = questionnair;
        this.id = id;
        this.timeOfCreation = timeOfCreation;
    }

    public StandardQuiz(User participant, Questionnair questionnair) {
        this.participant = participant;
        this.questionnair = questionnair;
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
    public Questionnair getQuestionnair() {
        return questionnair;
    }

    @Override
    public UUID getId() {
        return id;
    }
}
