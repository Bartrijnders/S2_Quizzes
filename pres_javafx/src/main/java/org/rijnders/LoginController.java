package org.rijnders;

import com.rijnders.entityinterfaces.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sevices.LoginService;

import java.io.IOException;

public class LoginController {

    @FXML public TextField email_txtfield;
    @FXML public PasswordField password_pswrdField;

    @FXML
    public void loginBtnClick(){
        LoginService loginService = new LoginService();
        User user = loginService.loginWithEmail(email_txtfield.getText(), password_pswrdField.getText());
        if(user == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Email or password is incorrect. try again.");
            alert.show();
        }
        else {
            //TODO pass user through to the next controller

        }
    }

    @FXML
    public void registerBtnClick() throws IOException {
        App.setRoot("register");
    }

}
