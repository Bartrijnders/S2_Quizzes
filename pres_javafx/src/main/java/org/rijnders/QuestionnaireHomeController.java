package org.rijnders;

import com.rijnders.entityinterfaces.Questionnaire;
import com.rijnders.entityinterfaces.User;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import sevices.ActiveUserService;
import sevices.QuestionnaireService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class QuestionnaireHomeController implements Initializable {

    @FXML
    public ListView<Object> questionnaireListView;
    @FXML
    public Button newQuestionnaireBtn;
    private List<Questionnaire> questionnaireList;

    public QuestionnaireHomeController() {
        User activeUser = ActiveUserService.getInstance().getUser();
        QuestionnaireService questionnaireService = new QuestionnaireService();
        try {
            questionnaireList = questionnaireService.getAllUsersQuestionnaires(activeUser);
        } catch (SQLException exception) {
            Alert alert = ExceptionAlert.getInstance().newSQLAlert(exception);
            alert.show();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (!questionnaireList.isEmpty()) {
            for (Questionnaire questionnaire : questionnaireList) {
                questionnaireListView.getItems().add(questionnaire);
            }
        } else {
            questionnaireListView.getItems().add("no questionnaires found");
        }

        questionnaireListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 2) {
                    try {
                        App.setRoot("editQuestionnairePage");
                        Object controller = App.getController();
                        if (controller instanceof EditQuestionnairePageController) {
                            ((EditQuestionnairePageController) controller).setQuestionnaire((Questionnaire) questionnaireListView.getSelectionModel().getSelectedItem());
                        }
                    } catch (IOException e) {
                        Alert alert = ExceptionAlert.getInstance().newIOAlert(e);
                        alert.show();
                    }
                }
            }
        });
    }

    public void newQuestionnaireBtnClick() throws IOException {
        App.setRoot("newQuestionnairePage");
    }

    public void backBtnClick() throws IOException {
        App.setRoot("home");
    }
}
