package com.rijnders.entities;

import com.rijnders.entityinterfaces.Answer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MultipleChoiceQuestion extends StandardQuestion{

    private List<Answer> answers;

    public MultipleChoiceQuestion(String questionLine, UUID id, List<Answer> answers) {
        super(questionLine, id);
        this.answers = answers;
        this.setType(QuestionType.MULTIPLECHOICE);
    }

    public MultipleChoiceQuestion(String questionLine, List<Answer> answers) {
        super(questionLine);
        this.answers = answers;
        this.setType(QuestionType.MULTIPLECHOICE);
    }

    public MultipleChoiceQuestion(String questionLine, UUID id) {
        super(questionLine, id);
        this.setType(QuestionType.MULTIPLECHOICE);
        this.answers = new ArrayList<>();
    }

    public MultipleChoiceQuestion(String questionLine) {
        super(questionLine);
        this.setType(QuestionType.MULTIPLECHOICE);
        this.answers = new ArrayList<>();
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
