package org.rijnders;

import com.rijnders.entityinterfaces.Questionnaire;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import session.SessionAble;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PlayHomeController implements SetSessionAble, Initializable {
    @FXML
    public Button playBtn;
    @FXML
    public Button backBtn;
    @FXML
    public ListView<Questionnaire> questionnaireListView;
    @FXML
    public Button resultsBtn;
    private SessionAble session;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resultsBtn.setDisable(true);
        questionnaireListView.getSelectionModel().selectedItemProperty().addListener((observableValue, questionnaire, t1) -> playBtn.setDisable(questionnaireListView.getSelectionModel().getSelectedItem() == null));
        questionnaireListView.getSelectionModel().selectedItemProperty().addListener((observableValue, questionnaire, t1) -> resultsBtn.setDisable(questionnaireListView.getSelectionModel().getSelectedItem() == null));
    }

    public void setBackBtn() {
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

    public void setPlayBtn() throws IOException, SQLException {
        Questionnaire selectedItem = questionnaireListView.getSelectionModel().getSelectedItem();
        SceneSwitchAble sceneSwitchAble = new SceneSwitcher();
        sceneSwitchAble.switchTo(session, FileNameHandler.QUIZ_PLAY);
        Object controller = App.getController();
        if (controller instanceof QuizPlayController) {
            ((QuizPlayController) controller).setQuestionnaire(selectedItem);
        }
    }

    public void setResultsBtn() {
        SceneSwitchAble sceneSwitchAble = new SceneSwitcher();
        sceneSwitchAble.switchTo(session, "resultsQuestionnairePage");
        Object controller = App.getController();
        if (controller instanceof ResultQuestionnaireController) {
            Questionnaire selectedItem = questionnaireListView.getSelectionModel().getSelectedItem();
            ((ResultQuestionnaireController) controller).setQuestionnaire(selectedItem);
        }
    }
}
