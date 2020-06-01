package session;

import com.rijnders.entityinterfaces.Questionnaire;

import java.util.List;

public interface QuestionnaireCollectionManageAble {
    List<Questionnaire> getQCollection();

    void setQCollection(List<Questionnaire> qCollection);
}
