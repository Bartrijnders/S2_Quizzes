package com.rijnders.entities;

import com.rijnders.entityinterfaces.Questionnaire;
import com.rijnders.entityinterfaces.Quiz;
import com.rijnders.entityinterfaces.Turn;
import com.rijnders.entityinterfaces.User;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StandardQuiz implements Quiz {

    private final User participant;
    private final List<Turn> turns;
    private final Questionnaire questionnaire;
    private final UUID id;
    private final OffsetDateTime dateTime;
    private int score;


    public StandardQuiz(User participant, List<Turn> turns, Questionnaire questionnaire, UUID id, OffsetDateTime timeOfCreation, int score) {
        this.participant = participant;
        this.turns = turns;
        this.questionnaire = questionnaire;
        this.id = id;
        this.score = score;
        this.dateTime = timeOfCreation;
    }

    public StandardQuiz(User participant, Questionnaire questionnaire) {
        this.participant = participant;
        this.questionnaire = questionnaire;
        this.id = UUID.randomUUID();
        this.turns = new ArrayList<>();
        score = 0;
        this.dateTime = OffsetDateTime.now();
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
    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public OffsetDateTime getTime() {
        return dateTime;
    }
}
