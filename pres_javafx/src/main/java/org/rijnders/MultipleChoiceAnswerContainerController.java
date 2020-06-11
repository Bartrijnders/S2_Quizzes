package org.rijnders;

import com.rijnders.entities.MultipleChoiceQuestion;
import com.rijnders.entityinterfaces.Turn;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MultipleChoiceAnswerContainerController implements Initializable {

    @FXML
    public GridPane answersGridPane;
    private Turn turn;
    private QuizPlayController parentController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        answersGridPane.setVgap(10);
        answersGridPane.setHgap(10);
    }

    public void setTurn(Turn turn) throws IOException {
        this.turn = turn;
        int answerIndex = 0;
        MultipleChoiceQuestion question = (MultipleChoiceQuestion) turn.getQuestion();
        for (int i = 0; i < answersGridPane.getColumnCount(); i++) {
            for (int j = 0; j < answersGridPane.getRowCount(); j++) {
                Button node = FXMLLoader.load(getClass().getResource("multiChoiceAnswerButton.fxml"));
                Object controller = node.getUserData();
                if (controller instanceof MultiChoiceButtonController) {
                    ((MultiChoiceButtonController) controller).setAnswerContainer(this);
                    ((MultiChoiceButtonController) controller).setAnswer(question.getAnswers().get(answerIndex));
                    ((MultiChoiceButtonController) controller).setTurn(turn);
                    answerIndex++;
                }
                answersGridPane.add(node, i, j);
            }
        }
    }


    public void setParentController(QuizPlayController controller) {
        this.parentController = controller;
    }

    public void passThroughAnswer(String answer) throws SQLException {
        parentController.multipleChoiceAnswered(answer);
    }
}
