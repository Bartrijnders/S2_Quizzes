package org.rijnders;

import com.rijnders.entityinterfaces.Questionnaire;
import com.rijnders.entityinterfaces.Quiz;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import session.SessionAble;
import sevices.QuizService;
import sevices.QuizServiceAble;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ResultQuestionnaireController implements SetSessionAble {

    @FXML
    public ListView<AnchorPane> quizListView;

    @FXML
    public Button backBtn;

    @FXML
    public Button showResultsButton;

    private Questionnaire questionnaire;
    private List<Quiz> quizzes;
    private SessionAble session;

    public void setQuestionnaire(Questionnaire questionnaire) {
        try {
            this.questionnaire = questionnaire;
            QuizServiceAble quizServiceAble = new QuizService();
            quizzes = quizServiceAble.getQuizzesFromQuestionnaire(questionnaire.getId());
            for (Quiz quiz : quizzes) {
                AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("quizContainer.fxml"));
                Object controller = anchorPane.getUserData();
                quizListView.getItems().add(anchorPane);
                if (controller instanceof QuizContainerController) {
                    ((QuizContainerController) controller).setQuiz(quiz);
                }
            }

        } catch (SQLException sqlException) {
            Alert alert = ExceptionAlert.getInstance().newSQLAlert(sqlException);
            alert.show();
        } catch (IOException ioException) {
            Alert alert = ExceptionAlert.getInstance().newIOAlert(ioException);
            alert.show();
        }
    }

    @Override
    public void setSession(SessionAble session) {
        this.session = session;
    }

    public void setBackBtn() {
        SceneSwitchAble sceneSwitcher = new SceneSwitcher();
        sceneSwitcher.switchTo(session, FileNameHandler.PLAY_HOME);
    }

    public void setShowResultsButton() {
        AnchorPane selected = quizListView.getSelectionModel().getSelectedItem();
        Object controller = selected.getUserData();
        try {
            if (controller instanceof QuizContainerController) {
                Quiz quiz = ((QuizContainerController) controller).getQuiz();
                SceneSwitchAble sceneSwitchAble = new SceneSwitcher();
                sceneSwitchAble.switchTo(session, "resultsQuizPage");
                controller = App.getController();
                if (controller instanceof QuizResultsController) {
                    ((QuizResultsController) controller).setQuiz(quiz);
                }
            }
        } catch (IOException e) {
            Alert alert = ExceptionAlert.getInstance().newIOAlert(e);
            alert.show();
        }

    }
}
