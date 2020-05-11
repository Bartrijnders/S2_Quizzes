package com.rijnders.questionnaire;

import com.rijnders.entities.MultipleChoiceQuestion;
import com.rijnders.entities.OpenQuestion;
import com.rijnders.entities.StandardQuestion;
import com.rijnders.entityinterfaces.Answer;
import com.rijnders.entityinterfaces.Questionnair;

public class QuestionnaireInserter {
    private InsertingQuestionnareDao insertingQuestionnareDao;
    private InsertAnswerDao insertAnswerDao;
    private InsertingQuetionDao insertingQuetionDao;

    public QuestionnaireInserter() {
        this.insertingQuestionnareDao = new InsertingQuestionnareDao();
        this.insertAnswerDao = new InsertAnswerDao();
        this.insertingQuetionDao = new InsertingQuetionDao();
    }

    public void insert(Questionnair questionnair){
        insertingQuestionnareDao.insertQuetionnaire(questionnair);
        for(StandardQuestion question : questionnair.getQuestions()){
            insertingQuetionDao.insertQuetionnaire(question, questionnair);
            if(question instanceof OpenQuestion){
                insertAnswerDao.insertAnswer(((OpenQuestion) question).getAnswer(), question);
            } else
                if(question instanceof MultipleChoiceQuestion){
                    for(Answer answer : ((MultipleChoiceQuestion) question).getAnswers()){
                        insertAnswerDao.insertAnswer(answer, question);
                    }
                }
        }
    }
}
