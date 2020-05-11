package com.rijnders.entities;

import com.rijnders.entityinterfaces.Answer;

import java.util.UUID;

public class StandardAnswer implements Answer {

    private String answerLine;
    private boolean isCorrect;
    private UUID id;

    public StandardAnswer(String answerLine, boolean isCorrect, UUID id) {
        this.answerLine = answerLine;
        this.isCorrect = isCorrect;
        this.id = id;
    }

    public StandardAnswer(String answerLine, boolean isCorrect) {
        this.answerLine = answerLine;
        this.isCorrect = isCorrect;
    }

    public String getAnswerLine() {
        return answerLine;
    }

    public void setAnswerLine(String answerLine) {
        this.answerLine = answerLine;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public UUID getId() {
        return id;
    }
}
