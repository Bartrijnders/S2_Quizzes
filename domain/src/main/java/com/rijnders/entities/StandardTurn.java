package com.rijnders.entities;

import com.rijnders.entityinterfaces.Answer;
import com.rijnders.entityinterfaces.Turn;

public class StandardTurn implements Turn {

    private final int number;
    private final StandardQuestion question;
    private Answer chosenAnswer;

    public StandardTurn(int number, StandardQuestion question, Answer chosenAnswer) {
        this.number = number;
        this.question = question;
        this.chosenAnswer = chosenAnswer;
    }

    public int getNumber() {
        return number;
    }

    public StandardQuestion getQuestion() {
        return question;
    }

    public Answer getChosenAnswer() {
        return chosenAnswer;
    }

    public void setChosenAnswer(Answer chosenAnswer) {
        this.chosenAnswer = chosenAnswer;
    }
}
