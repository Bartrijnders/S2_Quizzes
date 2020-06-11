package com.rijnders.entityinterfaces;

import com.rijnders.entities.StandardQuestion;

import java.util.UUID;

public interface Turn {
    int getNumber();

    StandardQuestion getQuestion();

    String getChosenAnswer();

    void setChosenAnswer(String chosenAnswer);

    boolean isCorrect();

    void setCorrect(boolean correct);

    UUID getQuizId();
}
