package org.rijnders;

import com.rijnders.entities.QuestionType;
import com.rijnders.entityinterfaces.Questionnaire;
import com.rijnders.entityinterfaces.Turn;
import gameplay.QuizGame;
import gameplay.QuizGameAble;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import session.SessionAble;

import java.io.IOException;
import java.sql.SQLException;

public class QuizPlayController implements SetSessionAble {

    @FXML
    public Label questionLabel;
    @FXML
    public AnchorPane answerAnchorPane;
    private SessionAble session;
    private Questionnaire chosenQuestionnaire;
    private QuizGameAble quizGame;
    private Turn activeTurn;

    public void setQuizGame() {
        SceneSwitchAble sceneSwitchAble = new SceneSwitcher();
        sceneSwitchAble.switchTo(session, FileNameHandler.PLAY_HOME);
    }

    public void setQuestionnaire(Questionnaire questionnaire) throws IOException, SQLException {
        this.chosenQuestionnaire = questionnaire;
        questionLabel.setText(questionnaire.getName());
        if (session != null) {
            quizGame = new QuizGame(session.getActiveUserService().getUser(), chosenQuestionnaire);
            showTurn();
        }
    }

    @Override
    public void setSession(SessionAble session) throws IOException, SQLException {
        this.session = session;
        if (chosenQuestionnaire != null) {
            quizGame = new QuizGame(session.getActiveUserService().getUser(), chosenQuestionnaire);
            showTurn();
        }
    }

    private void newTurn() {
        //activeTurn = quizGame.getNextTurn();
        if (activeTurn == null) {
            //TODO end game and show results
        }
    }

    private void showTurn() {
        try {
            questionLabel.setText(quizGame.getActiveTurn().getQuestion().getQuestionLine());
            if (quizGame.getActiveTurn().getQuestion().getType() == QuestionType.MULTIPLECHOICE) {
                GridPane gridPane = FXMLLoader.load(getClass().getResource("multipleChoiceAnswerContainer.fxml"));
                Object controller = gridPane.getUserData();
                if (controller instanceof MultipleChoiceAnswerContainerController) {
                    ((MultipleChoiceAnswerContainerController) controller).setTurn(quizGame.getActiveTurn());
                    ((MultipleChoiceAnswerContainerController) controller).setParentController((QuizPlayController) App.getController());
                }
                answerAnchorPane.getChildren().clear();
                answerAnchorPane.getChildren().add(gridPane);
            } else if (quizGame.getActiveTurn().getQuestion().getType() == QuestionType.OPEN) {
                VBox vBox = FXMLLoader.load(getClass().getResource("openQuestionContainer.fxml"));
                Object controller = vBox.getUserData();
                if (controller instanceof OpenQuestionContainerController) {
                    ((OpenQuestionContainerController) controller).setTurn(quizGame.getActiveTurn());
                    ((OpenQuestionContainerController) controller).setParentController((QuizPlayController) App.getController());
                }
                answerAnchorPane.getChildren().clear();
                answerAnchorPane.getChildren().add(vBox);
            }
        } catch (IOException e) {
            Alert alert = ExceptionAlert.getInstance().newIOAlert(e);
            alert.showAndWait();
        }
    }


    public void openQuestionAnswered(String answer) throws SQLException {
        boolean end = quizGame.answerOpenQuestion(answer);
        checkIfEnd(end);
    }


    public void multipleChoiceAnswered(String answer) throws SQLException {
        boolean end = quizGame.answerMultipleChoiceQuestion(answer);
        checkIfEnd(end);
    }

    private void checkIfEnd(boolean end) {
        if (!end) {
            showTurn();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(String.valueOf(quizGame.getQuiz().getScore()));
            alert.showAndWait();
        }
    }
}
