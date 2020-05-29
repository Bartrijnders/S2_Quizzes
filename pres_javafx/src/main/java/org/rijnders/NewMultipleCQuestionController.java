package org.rijnders;

import com.rijnders.entities.MultipleChoiceQuestion;
import com.rijnders.entities.StandardAnswer;
import com.rijnders.entityinterfaces.Answer;
import com.rijnders.entityinterfaces.Questionnaire;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class NewMultipleCQuestionController extends Group implements Initializable, ContentCheckAble, CreateAble<MultipleChoiceQuestion, Questionnaire> {

    @FXML
    public TextField questionTextField;
    @FXML
    public TextField answerATextField;
    @FXML
    public TextField answerBTextField;
    @FXML
    public TextField answerCTextField;
    @FXML
    public TextField answerDTextField;
    @FXML
    public RadioButton answerARadioButton;
    @FXML
    public RadioButton answerBRadioButton;
    @FXML
    public RadioButton answerCRadioButton;
    @FXML
    public RadioButton answerDRadioButton;
    @FXML
    public VBox anchorVBox;
    @FXML
    public GridPane anchorGridPane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //not used anymore
    }

    @Override
    public boolean check() {
        boolean output = true;
        String style = "-fx-text-fill: red; -fx-border-color: red; ";
        String containerStyle = "-fx-border-radius: 10px; -fx-border-width: 3px;";
        for (Node node : anchorVBox.getChildren()) {
            if (node instanceof TextField && ((TextField) node).getText().isEmpty()) {
                output = false;
                node.setStyle(style);
                anchorVBox.setStyle(style + containerStyle);
            }
        }
        for (Node node : anchorGridPane.getChildren()) {
            if (node instanceof TextField && ((TextField) node).getText().isEmpty()) {
                output = false;
                node.setStyle(style);
                anchorVBox.setStyle(style + containerStyle);
            }
        }
        return output;
    }

    @Override
    public MultipleChoiceQuestion create(Questionnaire questionnaire) {
        if (check()) {
            List<Answer> answers = new ArrayList<>();
            MultipleChoiceQuestion question = new MultipleChoiceQuestion(questionTextField.getText(), questionnaire.getId());
            answers.add(new StandardAnswer(answerATextField.getText(), answerARadioButton.isSelected(), question.getId()));
            answers.add(new StandardAnswer(answerBTextField.getText(), answerBRadioButton.isSelected(), question.getId()));
            answers.add(new StandardAnswer(answerCTextField.getText(), answerCRadioButton.isSelected(), question.getId()));
            answers.add(new StandardAnswer(answerDTextField.getText(), answerDRadioButton.isSelected(), question.getId()));
            question.getAnswers().addAll(answers);
            return question;
        } else {
            return null;
        }
    }
}
