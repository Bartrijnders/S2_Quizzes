package org.rijnders;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OpenQuestionControlController extends Group implements Initializable {

    @FXML public TextField questionTextField;
    @FXML public TextField answerTextField;
    @FXML public Group group;

    public OpenQuestionControlController() throws IOException {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
