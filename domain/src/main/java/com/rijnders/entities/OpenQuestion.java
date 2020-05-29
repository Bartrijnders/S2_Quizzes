package com.rijnders.entities;

import com.rijnders.entityinterfaces.Answer;

import java.util.UUID;

public class OpenQuestion extends StandardQuestion implements answerGetAble<Answer> {

    private Answer answer;

    public OpenQuestion(String questionLine, UUID id, UUID questionnaireId, Answer answer) {
        super(questionLine, id, questionnaireId);
        this.answer = answer;
        this.setType(QuestionType.OPEN);
    }

    public OpenQuestion(String questionLine, UUID questionnaireId) {
        super(questionLine, questionnaireId);
        this.answer = null;
        this.setType(QuestionType.OPEN);
    }


    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    @Override
    public Answer getAnswers() {
        return getAnswer();
    }
}
