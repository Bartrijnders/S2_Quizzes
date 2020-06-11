package org.rijnders;

import com.rijnders.entities.OpenQuestion;
import com.rijnders.entityinterfaces.Questionnaire;
import factories.AnswerFactory;
import factories.AnswerFactoryAble;
import factories.OpenQuestionFactory;
import factories.OpenQuestionFactoryAble;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.TextField;

public class OpenQuestionControlController extends Group implements ContentCheckAble, CreateAble<OpenQuestion, Questionnaire> {

    @FXML
    public TextField questionTextField;
    @FXML
    public TextField answerTextField;
    @FXML
    public Group group;




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
            OpenQuestionFactoryAble questionFactory = new OpenQuestionFactory();
            OpenQuestion question = questionFactory.create(questionTextField.getText(), questionnaire);
            AnswerFactoryAble answerFactoryAble = new AnswerFactory();
            question.setAnswer(answerFactoryAble.create(answerTextField.getText(), question.getId(), true));
            return question;

        } else {
            return null;
        }
    }

    public void fillIn(OpenQuestion openQuestion) {
        questionTextField.setText(openQuestion.getQuestionLine());
        answerTextField.setText(openQuestion.getAnswer().getAnswerLine());
    }

}
