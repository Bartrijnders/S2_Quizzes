package org.rijnders;

import com.rijnders.entityinterfaces.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sevices.ActiveUserService;
import sevices.LoginService;

import java.io.IOException;

public class LoginController {

    @FXML public TextField emailTxtF;
    @FXML public PasswordField passwordPswrdF;


    @FXML
    public void loginBtnClick() {
        LoginService loginService = new LoginService();
        User user = loginService.loginWithEmail(emailTxtF.getText(), passwordPswrdF.getText());
        if(user == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Email or password is incorrect. try again.");
            alert.show();
        }
        else {
            ActiveUserService activeUserService = ActiveUserService.getInstance();
            activeUserService.setUser(user);
            try{
                App.setRoot("home");
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

    @FXML
    public void registerBtnClick() throws IOException {
        App.setRoot("register");
    }

}
