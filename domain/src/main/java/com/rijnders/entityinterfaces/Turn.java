package com.rijnders.entityinterfaces;

import com.rijnders.entities.StandardQuestion;

public interface Turn {
    int getNumber();

    StandardQuestion getQuestion();

    Answer getChosenAnswer();

    void setChosenAnswer(Answer chosenAnswer);
}
