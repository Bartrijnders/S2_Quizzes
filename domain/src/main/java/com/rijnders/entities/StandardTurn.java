package com.rijnders.entities;

import com.rijnders.entityinterfaces.Turn;

import java.util.UUID;

public class StandardTurn implements Turn {

    private final int number;
    private final StandardQuestion question;
    private final UUID quizId;
    private String chosenAnswer;
    private boolean isCorrect;

    public StandardTurn(int number, StandardQuestion question, String chosenAnswer, boolean isCorrect, UUID quizid) {
        this.number = number;
        this.question = question;
        this.chosenAnswer = chosenAnswer;
        this.quizId = quizid;
        this.isCorrect = isCorrect;
    }

    public StandardTurn(int number, StandardQuestion question, UUID quizId) {
        this.number = number;
        this.question = question;
        this.quizId = quizId;
        this.chosenAnswer = null;
        this.isCorrect = false;
    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public StandardQuestion getQuestion() {
        return question;
    }

    @Override
    public String getChosenAnswer() {
        return chosenAnswer;
    }

    @Override
    public void setChosenAnswer(String chosenAnswer) {
        this.chosenAnswer = chosenAnswer;
    }

    @Override
    public boolean isCorrect() {
        return isCorrect;
    }

    @Override
    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    @Override
    public UUID getQuizId() {
        return quizId;
    }
}
