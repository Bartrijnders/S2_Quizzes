package org.rijnders;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import session.SessionAble;

public class HomeController implements SetSessionAble {


    @FXML
    public Text welcomeTxt;
    @FXML
    public Button myQuestionnaireBtn;
    @FXML
    public Button playQuizBtn;
    private SessionAble session;


    private void setWelcomeTxt(){
        welcomeTxt.setText("Welcome " + session.getActiveUserService().getUser().getUserName());
    }

    @FXML
    public void setMyQuestionnaireBtnClick() {
        SceneSwitchAble sceneSwitchAble = new SceneSwitcher();
        sceneSwitchAble.switchTo(session, FileNameHandler.QUESTIONNAIRE_HOME);
    }

    public void setPlayQuizBtnClick() {
        SceneSwitchAble switcher = new SceneSwitcher();
        switcher.switchTo(session, FileNameHandler.PLAY_HOME);
    }

    @Override
    public void setSession(SessionAble session) {
        this.session = session;
        setWelcomeTxt();
    }
}
