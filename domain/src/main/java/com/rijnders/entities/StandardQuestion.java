package com.rijnders.entities;

import java.util.UUID;

public abstract class StandardQuestion {

    private String questionLine;
    private QuestionType type;
    private UUID id;

    public StandardQuestion(String questionLine, UUID id) {
        this.questionLine = questionLine;
        this.id = id;
    }

    public StandardQuestion(String questionLine) {
        this.questionLine = questionLine;
        this.id = UUID.randomUUID();
    }

    public String getQuestionLine() {
        return questionLine;
    }

    public void setQuestionLine(String questionLine) {
        this.questionLine = questionLine;
    }

    public UUID getId() {
        return id;
    }

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }
}
