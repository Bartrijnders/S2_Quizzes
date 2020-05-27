package com.rijnders.entities;

import com.rijnders.entityinterfaces.Answer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OpenQuestion extends StandardQuestion {

    private Answer answer;

    public OpenQuestion(String questionLine, UUID id, UUID questionnaireId, Answer answer) {
        super(questionLine, id, questionnaireId);
        this.answer = answer;
        this.setType(QuestionType.OPEN);
    }

    public OpenQuestion(String questionLine, UUID questionnaireId, Answer answer) {
        super(questionLine, questionnaireId);
        this.answer = answer;
        this.setType(QuestionType.OPEN);
    }


    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(StandardAnswer answer) {
        this.answer = answer;
    }

    @Override
    public List<Answer> getAnswers() {
        List<Answer> answers = new ArrayList<>();
        answers.add(getAnswer());
        return answers;
    }
}
