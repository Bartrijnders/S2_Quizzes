package org.rijnders;

import com.rijnders.entities.MultipleChoiceQuestion;
import com.rijnders.entityinterfaces.Questionnaire;
import factories.AnswerFactory;
import factories.AnswerFactoryAble;
import factories.MultipleChoiceQuestionFactory;
import factories.MultipleChoiceQuestionFactoryAble;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class NewMultipleCQuestionController extends Group implements ContentCheckAble, CreateAble<MultipleChoiceQuestion, Questionnaire> {

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
            MultipleChoiceQuestionFactoryAble multipleChoiceQuestionFactoryAble = new MultipleChoiceQuestionFactory();
            MultipleChoiceQuestion multipleChoiceQuestion = multipleChoiceQuestionFactoryAble.create(questionTextField.getText(), questionnaire.getId());
            AnswerFactoryAble answerFactoryAble = new AnswerFactory();
            multipleChoiceQuestion.getAnswers().add(answerFactoryAble.create(answerATextField.getText(), multipleChoiceQuestion.getId(), answerARadioButton.isSelected()));
            multipleChoiceQuestion.getAnswers().add(answerFactoryAble.create(answerBTextField.getText(), multipleChoiceQuestion.getId(), answerBRadioButton.isSelected()));
            multipleChoiceQuestion.getAnswers().add(answerFactoryAble.create(answerCTextField.getText(), multipleChoiceQuestion.getId(), answerCRadioButton.isSelected()));
            multipleChoiceQuestion.getAnswers().add(answerFactoryAble.create(answerDTextField.getText(), multipleChoiceQuestion.getId(), answerDRadioButton.isSelected()));
            return multipleChoiceQuestion;
        } else {
            return null;
        }
    }

    public void fillIn(MultipleChoiceQuestion question) {
        answerATextField.setText(question.getAnswers().get(0).getAnswerLine());
        answerARadioButton.setSelected(question.getAnswers().get(0).isCorrect());

        answerBTextField.setText(question.getAnswers().get(1).getAnswerLine());
        answerBRadioButton.setSelected(question.getAnswers().get(1).isCorrect());

        answerCTextField.setText(question.getAnswers().get(2).getAnswerLine());
        answerCRadioButton.setSelected(question.getAnswers().get(2).isCorrect());

        answerDTextField.setText(question.getAnswers().get(3).getAnswerLine());
        answerDRadioButton.setSelected(question.getAnswers().get(3).isCorrect());

        questionTextField.setText(question.getQuestionLine());

    }
}
