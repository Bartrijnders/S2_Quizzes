package com.rijnders.entities;

import com.rijnders.entityinterfaces.Answer;

import java.util.UUID;

public class StandardAnswer implements Answer {

    private String answerLine;
    private boolean isCorrect;
    private final UUID id;
    private UUID questionId;

    public StandardAnswer(String answerLine, boolean isCorrect, UUID id, UUID questionId) {
        this.answerLine = answerLine;
        this.isCorrect = isCorrect;
        this.id = id;
        this.questionId = questionId;
    }

    public StandardAnswer(String answerLine, boolean isCorrect, UUID questionId) {
        this.questionId = questionId;
        this.answerLine = answerLine;
        this.isCorrect = isCorrect;
        this.id = UUID.randomUUID();
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

    @Override
    public UUID getQuestionId() {
        return questionId;
    }

    public void setQuestionId(UUID questionId) {
        this.questionId = questionId;
    }
}
