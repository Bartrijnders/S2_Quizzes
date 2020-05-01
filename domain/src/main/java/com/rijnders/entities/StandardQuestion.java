package com.rijnders.entities;

public abstract class StandardQuestion {

    private String questionLine;
    private int number;

    public StandardQuestion(String questionLine, int number) {
        this.questionLine = questionLine;
        this.number = number;
    }

    public String getQuestionLine() {
        return questionLine;
    }

    public void setQuestionLine(String questionLine) {
        this.questionLine = questionLine;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
