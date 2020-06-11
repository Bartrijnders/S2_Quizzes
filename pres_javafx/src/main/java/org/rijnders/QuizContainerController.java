package org.rijnders;

import com.rijnders.entityinterfaces.Quiz;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.format.DateTimeFormatter;

public class QuizContainerController {
    @FXML
    public TextField scoreTxtField;

    @FXML
    public Label timeLbl;

    private Quiz quiz;

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
        this.scoreTxtField.setText(String.valueOf(quiz.getScore()));
        this.timeLbl.setText(quiz.getTime().format(DateTimeFormatter.ofPattern("dd-MM-yy hh:mm")));
    }
}
