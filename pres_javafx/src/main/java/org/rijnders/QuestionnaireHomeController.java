package org.rijnders;

import com.rijnders.entityinterfaces.Questionnaire;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import session.SessionAble;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class QuestionnaireHomeController implements Initializable, SetSessionAble {

    @FXML
    public ListView<Questionnaire> questionnaireListView;
    @FXML
    public Button newQuestionnaireBtn;
    private SessionAble session;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        questionnaireListView.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getClickCount() == 2 && !questionnaireListView.getSelectionModel().isEmpty()) {
                try {
                    App.setRoot(FileNameHandler.EDIT_QUESTIONNAIRE_PAGE);
                    SetSessionAble controller = (SetSessionAble) App.getController();
                    assert controller != null;
                    controller.setSession(session);
                    if (controller instanceof EditQuestionnairePageController) {
                        ((EditQuestionnairePageController) controller).setQuestionnaire(questionnaireListView.getSelectionModel().getSelectedItem());
                    }
                } catch (IOException e) {
                    Alert alert = ExceptionAlert.getInstance().newIOAlert(e);
                    alert.show();
                } catch (SQLException sqlException) {
                    Alert alert = ExceptionAlert.getInstance().newSQLAlert(sqlException);
                    alert.show();
                }
            }
        });
    }

    public void newQuestionnaireBtnClick() {
        SceneSwitchAble sceneSwitchAble = new SceneSwitcher();
        sceneSwitchAble.switchTo(session, FileNameHandler.NEW_QUESTIONNAIRE_PAGE);
    }

    public void backBtnClick() {
        SceneSwitchAble sceneSwitchAble = new SceneSwitcher();
        sceneSwitchAble.switchTo(session, FileNameHandler.HOME);


    }

    @Override
    public void setSession(SessionAble session) {
        this.session = session;
        fillListView();
    }

    private void fillListView() {
        ObservableList<Questionnaire> questionnaires = FXCollections.observableArrayList(session.getActiveQuestionnaireService().getQCollection());
        questionnaireListView.setItems(questionnaires);
        questionnaireListView.setCellFactory(questionnaireListView1 -> new ListCellController());
    }

}
