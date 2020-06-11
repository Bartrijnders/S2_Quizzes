package org.rijnders;

import com.rijnders.entityinterfaces.Turn;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class TurnContainer {


    @FXML
    public AnchorPane anchorPane;
    @FXML
    public TextField questionTxtField;
    @FXML
    public TextField correctTxtField;
    @FXML
    public TextField youTxtField;


    private Turn turn;

    public void setTurn(Turn turn) {
        this.turn = turn;
        if (turn.isCorrect()) {
            youTxtField.setStyle("-fx-border-color: green;");
            anchorPane.setStyle("-fx-background-color: lightgreen");
        } else {
            youTxtField.setStyle("-fx-border-color: red");
            anchorPane.setStyle("-fx-background-color: lightpink");
        }

        questionTxtField.setText(turn.getQuestion().getQuestionLine());
        correctTxtField.setText(turn.getQuestion().getCorrectAnswer().getAnswerLine());
        youTxtField.setText(turn.getChosenAnswer());
    }


}
