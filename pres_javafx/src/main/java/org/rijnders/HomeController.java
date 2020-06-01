package org.rijnders;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import session.SessionAble;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable, SetSessionAble {


    @FXML
    public Text welcomeTxt;
    @FXML
    public Button myQuestionnaireBtn;
    @FXML
    public Button playQuizBtn;
    private SessionAble session;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    private void setWelcomeTxt(){
        welcomeTxt.setText("Welcome " + session.getActiveUserService().getUser().getUserName());
    }

    @FXML
    public void setMyQuestionnaireBtnClick(){
        try {
            App.setRoot("questionnaireHome");
            SetSessionAble cont = (SetSessionAble) App.getController();
            cont.setSession(session);

        } catch (IOException e) {
            Alert alert = ExceptionAlert.getInstance().newIOAlert(e);
            alert.show();
        }

    }

    public void setPlayQuizBtnClick() {
        // to quiz part of the app
    }

    @Override
    public void setSession(SessionAble session) {
        this.session = session;
        setWelcomeTxt();
    }
}
