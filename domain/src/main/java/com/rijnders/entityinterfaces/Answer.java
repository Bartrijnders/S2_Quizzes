package com.rijnders.entityinterfaces;

public interface Answer {
    String getAnswerLine();

    void setAnswerLine(String answerLine);

    boolean isCorrect();

    void setCorrect(boolean correct);
}
