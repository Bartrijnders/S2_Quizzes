package org.rijnders;

import com.rijnders.entityinterfaces.Quiz;
import com.rijnders.entityinterfaces.Turn;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import session.SessionAble;

import java.io.IOException;

public class QuizResultsController implements SetSessionAble {

    @FXML
    public ListView<AnchorPane> turnListView;
    @FXML
    public Label scoreLbl;
    @FXML
    public Button backBtn;

    private SessionAble session;
    private Quiz quiz;

    public void setQuiz(Quiz quiz) throws IOException {
        this.quiz = quiz;
        for (Turn turn : quiz.getTurns()) {
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("turnContainer.fxml"));
            Object controller = anchorPane.getUserData();
            if (controller instanceof TurnContainer) {
                ((TurnContainer) controller).setTurn(turn);
                turnListView.getItems().add(anchorPane);
                scoreLbl.setText("Score: " + quiz.getScore());
            }
        }
    }

    public void setBackBtn() {
        SceneSwitchAble sceneSwitchAble = new SceneSwitcher();
        sceneSwitchAble.switchTo(session, "resultsQuestionnairePage");
        Object controller = App.getController();
        if (controller instanceof ResultQuestionnaireController) {
            ((ResultQuestionnaireController) controller).setQuestionnaire(quiz.getQuestionnaire());
        }
    }

    @Override
    public void setSession(SessionAble session) {
        this.session = session;
    }
}
