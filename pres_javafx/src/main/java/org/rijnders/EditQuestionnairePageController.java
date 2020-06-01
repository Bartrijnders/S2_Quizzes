package org.rijnders;

import com.rijnders.entities.MultipleChoiceQuestion;
import com.rijnders.entities.OpenQuestion;
import com.rijnders.entities.StandardQuestion;
import com.rijnders.entities.StandardQuestionnaire;
import com.rijnders.entityinterfaces.Questionnaire;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.*;
import session.SessionAble;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;


public class EditQuestionnairePageController implements Initializable, SetSessionAble {
    private final List<Group> groupList;
    @FXML
    public TextField nameTextField;
    @FXML
    public ListView<Group> listView;
    @FXML
    public Button saveBtn;
    @FXML
    public Button backBtn;
    @FXML
    public Button addOpenBtn;
    @FXML
    public Button addMultipleChoiceBtn;
    @FXML
    public Button deleteBtn;

    @FXML
    public Button deleteQuestionnaireBtn;

    public Questionnaire workQuestionnaire;

    public SessionAble session;


    public EditQuestionnairePageController() {
        this.groupList = new ArrayList<>();
        this.workQuestionnaire = null;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Group>() {
            @Override
            public void changed(ObservableValue<? extends Group> observableValue, Group group, Group t1) {
                deleteBtn.setDisable(listView.getSelectionModel().getSelectedItem() == null);
            }
        });
    }

    public void setQuestionnaire(Questionnaire questionnaire) throws IOException {
        this.workQuestionnaire = questionnaire;
        fillIn();
    }

    public void fillIn() throws IOException {
        nameTextField.setText(workQuestionnaire.getName());
        for (StandardQuestion question : workQuestionnaire.getQuestions()) {
            if (question instanceof MultipleChoiceQuestion) {
                Group group = FXMLLoader.load(getClass().getResource("newMultipleCQuestion.fxml"));
                listView.getItems().add(group);
                groupList.add(group);
                Object controller = group.getUserData();
                if (controller instanceof NewMultipleCQuestionController) {
                    ((NewMultipleCQuestionController) controller).fillIn((MultipleChoiceQuestion) question);
                }
            }
            if (question instanceof OpenQuestion) {
                Group group = FXMLLoader.load(getClass().getResource("newOpenQuestionControl.fxml"));
                listView.getItems().add(group);
                groupList.add(group);
                Object controller = group.getUserData();
                if (controller instanceof OpenQuestionControlController) {
                    ((OpenQuestionControlController) controller).fillIn((OpenQuestion) question);
                }
            }
        }
    }

    public void backBtnClick() {
        try {
            App.setRoot("questionnaireHome");
            SetSessionAble cont = (SetSessionAble) App.getController();
            assert cont != null;
            cont.setSession(session);
        } catch (IOException e) {
            Alert alert = ExceptionAlert.getInstance().newIOAlert(e);
            alert.showAndWait();
        }
    }

    public void newOpenBtnClick() throws IOException {
        Group group = FXMLLoader.load(getClass().getResource("newOpenQuestionControl.fxml"));
        listView.getItems().add(group);
        groupList.add(group);
    }

    public void newMultipleCBtnClick() throws IOException {
        Group group = FXMLLoader.load(getClass().getResource("newMultipleCQuestion.fxml"));
        listView.getItems().add(group);
        groupList.add(group);
    }

    public void updateButtonClick() throws IOException, SQLException {
        UUID toUseId = workQuestionnaire.getId();
        session.getQuestionnaireService().delete(workQuestionnaire);
        boolean proceed = missingContentCheck();
        if (!proceed) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Not all requered fields are filled in!");
            alert.setContentText("Pleas fill in the red textboxes");
            alert.show();
        } else {
            List<StandardQuestion> questions = new ArrayList<>();
            Questionnaire questionnaire = new StandardQuestionnaire(toUseId, nameTextField.getText(), session.getActiveUserService().getUser());
            for (Group node : groupList) {
                Object controller = node.getUserData();
                if (controller instanceof CreateAble) {
                    questions.add(((CreateAble<StandardQuestion, Questionnaire>) controller).create(questionnaire));
                }
            }
            questionnaire.getQuestions().addAll(questions);
            session.getQuestionnaireService().save(questionnaire);
            App.setRoot("questionnaireHome");
            SetSessionAble controller = (SetSessionAble) App.getController();
            controller.setSession(session);
        }
    }

    private boolean missingContentCheck() {
        boolean proceed = true;
        if (nameTextField.getText().isEmpty()) {
            proceed = false;
            nameTextField.setStyle("-fx-border-color: red;");
        }
        for (Group node : groupList) {
            Object controller = null;
            controller = node.getUserData();
            if (controller instanceof ContentCheckAble) {
                boolean check = ((ContentCheckAble) controller).check();
                if (!check) {
                    proceed = false;
                }
            }
        }
        return proceed;
    }

    public void setDeleteBtn() {
        Group group = listView.getSelectionModel().getSelectedItem();
        groupList.remove(group);
        listView.getItems().remove(group);
    }

    @Override
    public void setSession(SessionAble session) {
        this.session = session;
    }

    public void setDeleteQuestionnaireBtn() throws SQLException, IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Are you sure?");
        alert.setContentText("You're about to delete this questionnaire. Are you sure?");
        ButtonType noBtn = new ButtonType("No", ButtonBar.ButtonData.NO);
        ButtonType yesBtn = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        alert.getButtonTypes().setAll(noBtn, yesBtn);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == yesBtn) {
            session.getQuestionnaireService().delete(workQuestionnaire);
            App.setRoot("questionnaireHome");
            SetSessionAble controller = (SetSessionAble) App.getController();
            controller.setSession(session);
        }


    }
}



