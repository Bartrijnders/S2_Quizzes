package org.rijnders;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NewQuestionnairePageController {

    @FXML public TextField nameTextField;
    @FXML public ListView listView;
    @FXML public Button saveBtn;
    @FXML public Button backBtn;
    @FXML public Button addOpenBtn;
    @FXML public Button addMultipleChoiceBtn;

    private List<Group> groupList;

    public NewQuestionnairePageController() {
        this.groupList = new ArrayList<>();
    }

    public void backBtnClick(){
        try{
            App.setRoot("questionnaireHome");
        } catch (IOException e) {
            e.printStackTrace();
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
}
