package org.rijnders;

import com.rijnders.entityinterfaces.Questionnaire;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import session.SessionAble;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class QuestionnaireHomeController implements Initializable, SetSessionAble {

    @FXML
    public ListView<Object> questionnaireListView;
    @FXML
    public Button newQuestionnaireBtn;
    private SessionAble session;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        questionnaireListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 2 && !questionnaireListView.getSelectionModel().isEmpty()) {
                    try {
                        App.setRoot("editQuestionnairePage");
                        SetSessionAble controller = (SetSessionAble) App.getController();
                        controller.setSession(session);
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
        try {
            App.setRoot("newQuestionnairePage");
            SetSessionAble cont = (SetSessionAble) App.getController();
            cont.setSession(session);

        } catch (IOException e) {
            Alert alert = ExceptionAlert.getInstance().newIOAlert(e);
            alert.show();
        }
    }

    public void backBtnClick() throws IOException {
        App.setRoot("home");
    }

    @Override
    public void setSession(SessionAble session) {
        this.session = session;
        if (!session.getActiveQuestionnaireService().getQCollection().isEmpty()) {
            for (Questionnaire questionnaire : session.getActiveQuestionnaireService().getQCollection()) {
                questionnaireListView.getItems().add(questionnaire);
            }
        } else {
            questionnaireListView.getItems().add("no questionnaires found");
        }
    }

}
