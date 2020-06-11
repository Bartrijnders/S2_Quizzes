package com.rijnders.entities;

import com.rijnders.entityinterfaces.Answer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MultipleChoiceQuestion extends StandardQuestion implements answerGetAble<List<Answer>> {

    private List<Answer> answers;

    public MultipleChoiceQuestion(String questionLine, UUID id, UUID questionnaireId, List<Answer> answers) {
        super(questionLine, id, questionnaireId);
        this.answers = answers;
        this.setType(QuestionType.MULTIPLECHOICE);
    }

    public MultipleChoiceQuestion(String questionLine, UUID questionnaireId) {
        super(questionLine, questionnaireId);
        this.answers = new ArrayList<>();
        this.setType(QuestionType.MULTIPLECHOICE);
    }

    @Override
    public List<Answer> getAnswers() {
        return answers;
    }


    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    @Override
    public Answer getCorrectAnswer() {
        return answers.stream().filter(Answer::isCorrect).findFirst().get();
    }
}
