package org.rijnders;

import com.rijnders.entityinterfaces.Answer;
import com.rijnders.entityinterfaces.Turn;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.sql.SQLException;

public class MultiChoiceButtonController {

    @FXML
    public Button answerButton;
    private Answer answer;
    private Turn activeTurn;
    private MultipleChoiceAnswerContainerController answerContainer;

    public void setAnswer(Answer answer) {
        this.answer = answer;
        answerButton.setText(answer.getAnswerLine());
    }

    public void setTurn(Turn activeTurn) {
        this.activeTurn = activeTurn;
    }

    public void setAnswerContainer(MultipleChoiceAnswerContainerController answerContainer) {
        this.answerContainer = answerContainer;
    }


    public void setAnswerButton() throws SQLException {
        activeTurn.setChosenAnswer(answer.getAnswerLine());
        answerContainer.passThroughAnswer(answer.getAnswerLine());
    }
}
