package org.rijnders;

import com.rijnders.entityinterfaces.Turn;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class OpenQuestionContainerController {
    @FXML
    public Button confirmButton;
    @FXML
    public TextField yourAnswerTextField;
    private Turn turn;
    private QuizPlayController parentController;

    public void setTurn(Turn activeTurn) {
        this.turn = activeTurn;
    }

    public void setParentController(QuizPlayController controller) {
        this.parentController = controller;
    }

    public void setConfirmButton() throws SQLException {
        String answer = yourAnswerTextField.getText();
        parentController.openQuestionAnswered(answer);
    }
}
