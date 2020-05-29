package com.rijnders.entities;

import java.util.UUID;

public abstract class StandardQuestion {

    private String questionLine;
    private QuestionType type;
    private final UUID id;
    private final UUID questionnairId;

    public StandardQuestion(String questionLine, UUID id, UUID questionnaireId) {
        this.questionLine = questionLine;
        this.id = id;
        this.questionnairId = questionnaireId;
    }

    public StandardQuestion(String questionLine, UUID questionnaireId) {
        this.questionLine = questionLine;
        this.id = UUID.randomUUID();
        this.questionnairId = questionnaireId;
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

    public UUID getQuestionnairId() {
        return questionnairId;
    }
}
