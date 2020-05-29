package org.rijnders;

import com.rijnders.entities.OpenQuestion;
import com.rijnders.entities.StandardAnswer;
import com.rijnders.entityinterfaces.Answer;
import com.rijnders.entityinterfaces.Questionnaire;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.TextField;
import sevices.StandardTextService;

import java.net.URL;
import java.util.ResourceBundle;

public class OpenQuestionControlController extends Group implements Initializable, ContentCheckAble, CreateAble<OpenQuestion, Questionnaire> {

    private final StandardTextService textService;
    @FXML
    public TextField questionTextField;
    @FXML
    public TextField answerTextField;
    @FXML
    public Group group;

    public OpenQuestionControlController() {
        textService = new StandardTextService();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @Override
    public boolean check() {
        boolean output = true;
        String style = "-fx-text-fill: red; -fx-border-color: red";
        if (questionTextField.getText().isEmpty()) {
            output = false;
            questionTextField.setStyle(style);
        }
        if (answerTextField.getText().isEmpty()) {
            output = false;
            answerTextField.setStyle(style);
        }

        return output;
    }

    @Override
    public OpenQuestion create(Questionnaire questionnaire) {
        if (check()) {
            OpenQuestion question = new OpenQuestion(questionTextField.getText(), questionnaire.getId());
            Answer answer = new StandardAnswer(answerTextField.getText(), true, question.getId());
            question.setAnswer(answer);
            return question;
        } else {
            return null;
        }
    }

}
