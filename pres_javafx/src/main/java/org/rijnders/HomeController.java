package org.rijnders;

import com.rijnders.entityinterfaces.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import sevices.ActiveUserService;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    ActiveUserService activeUserService = ActiveUserService.getInstance();
    private User activeUser = activeUserService.getUser();

    @FXML public Text welcomeTxt;
    @FXML public Button myQuestionnairBtn;
    @FXML public Button playQuizBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setWelcomeTxt();
    }

    private void setWelcomeTxt(){
        welcomeTxt.setText("Welcome " + activeUser.getUserName());
    }

    private void setMyQuestionnairBtnClick(){

    }

    private void setPlayQuizBtnClick(){

    }


}
