package com.rijnders.entities;

import java.util.UUID;

public class OpenQuestion extends StandardQuestion {

    private StandardAnswer answer;

    public OpenQuestion(String questionLine, UUID id, StandardAnswer answer) {
        super(questionLine, id);
        this.answer = answer;
        this.setType(QuestionType.OPEN);
    }

    public OpenQuestion(String questionLine, StandardAnswer answer) {
        super(questionLine);
        this.answer = answer;
        this.setType(QuestionType.OPEN);
    }

    public OpenQuestion(String questionLine, UUID id) {
        super(questionLine, id);
        this.setType(QuestionType.OPEN);
        this.answer = null;
    }

    public OpenQuestion(String questionLine) {
        super(questionLine);
        this.setType(QuestionType.OPEN);
        this.answer = null;
    }

    public StandardAnswer getAnswer() {
        return answer;
    }

    public void setAnswer(StandardAnswer answer) {
        this.answer = answer;
    }
}
