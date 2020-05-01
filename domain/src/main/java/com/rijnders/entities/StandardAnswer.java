package com.rijnders.entities;

import com.rijnders.entityinterfaces.Answer;

public class StandardAnswer implements Answer {

    private String answerLine;
    private boolean isCorrect;

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
}
