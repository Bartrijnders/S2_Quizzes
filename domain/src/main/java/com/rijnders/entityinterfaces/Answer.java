package com.rijnders.entityinterfaces;

import java.util.UUID;

public interface Answer {
    String getAnswerLine();

    void setAnswerLine(String answerLine);

    boolean isCorrect();

    void setCorrect(boolean correct);

    UUID getId();

    UUID getQuestionId();
}
