package com.rijnders.entities;

public class OpenQuestion extends StandardQuestion {

    private StandardAnswer answer;

    public OpenQuestion(String questionLine, int number, StandardAnswer answer) {
        super(questionLine, number);
        this.answer = answer;
    }

    public StandardAnswer getAnswer() {
        return answer;
    }

    public void setAnswer(StandardAnswer answer) {
        this.answer = answer;
    }
}
