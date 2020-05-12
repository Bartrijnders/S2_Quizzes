package org.rijnders;

import com.rijnders.entityinterfaces.Questionnair;
import com.rijnders.entityinterfaces.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import sevices.ActiveUserService;
import sevices.QuestionnaireService;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class QuestionnaireHomeController implements Initializable {

    @FXML public ListView questionnaireListView;
    @FXML public Button newQuestionnaireBtn;
    private User activeUser;
    private List<Questionnair> questionnairList;
    private QuestionnaireService questionnaireService;

    public QuestionnaireHomeController() {
        activeUser = ActiveUserService.getInstance().getUser();
        this.questionnaireService = new QuestionnaireService();
        questionnairList = questionnaireService.getAllUsersQuestionnaires(activeUser);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(!questionnairList.isEmpty()){
            for(Questionnair questionnair : questionnairList){
                questionnaireListView.getItems().add(questionnair);
            }
        }
        else{
            questionnaireListView.getItems().add("no questionnairs found");
        }
    }

    public void newQuestionnaireBtnClick() throws IOException {
        App.setRoot("newQuestionnairePage");
    }

    public void backBtnClick() throws IOException {
        App.setRoot("home");
    }
}
