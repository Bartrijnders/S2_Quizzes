package org.rijnders;

import com.rijnders.entities.StandardQuestion;
import com.rijnders.entities.StandardQuestionnaire;
import com.rijnders.entityinterfaces.Questionnaire;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import session.SessionAble;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class NewQuestionnairePageController implements Initializable, SetSessionAble {

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
    private SessionAble session;


    public NewQuestionnairePageController() {
        this.groupList = new ArrayList<>();
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

    public void saveButtonClick() throws IOException, SQLException {
        boolean proceed = missingContentCheck();
        if (!proceed) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Not all required fields are filled in!");
            alert.setContentText("Pleas fill in the red text boxes");
            alert.show();
        } else {
            List<StandardQuestion> questions = new ArrayList<>();
            Questionnaire questionnaire = new StandardQuestionnaire(nameTextField.getText(), session.getActiveUserService().getUser());
            for (Group node : groupList) {
                Object controller = null;
                controller = node.getUserData();
                if (controller instanceof CreateAble) {
                    questions.add(((CreateAble<StandardQuestion, Questionnaire>) controller).create(questionnaire));
                }
            }
            questionnaire.getQuestions().addAll(questions);
            session.getQuestionnaireService().save(questionnaire);
            session.getActiveQuestionnaireService().getQCollection().add(questionnaire);
            App.setRoot("questionnaireHome");
            SetSessionAble cont = (SetSessionAble) App.getController();
            assert cont != null;
            cont.setSession(session);


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
}
