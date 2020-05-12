package org.rijnders;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;

public class NewQuestionnairePageController {

    @FXML public TextField nameTextField;
    @FXML public ListView listView;
    @FXML public Button saveBtn;
    @FXML public Button backBtn;
    @FXML public Button addOpenBtn;
    @FXML public Button addMultipleChoiceBtn;

    public void backBtnClick(){
        try{
            App.setRoot("questionnaireHome");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
