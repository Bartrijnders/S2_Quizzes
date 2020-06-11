package session;

import sevices.QuestionnaireGetAllByUserService;

public interface QuestionnaireServiceAble {
    QuestionnaireGetAllByUserService getQuestionnaireService();

    void setQuestionnaireService(QuestionnaireGetAllByUserService questionnaireGetAllByUserService);
}
