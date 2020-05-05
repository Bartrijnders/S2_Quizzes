package org.rijnders;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import messages.RegisteryCheckMessage;
import sevices.RegisterService;

import java.io.IOException;

public class RegisterController {

    private final RegisterService registerService;
    @FXML public TextField usernameTxt;
    @FXML public TextField emailTxt;
    @FXML public PasswordField passwordTxt;
    @FXML public PasswordField repasswordPswdF;

    public RegisterController() {
        this.registerService = new RegisterService();
    }

    @FXML
    public void cancelBtnClick() throws IOException {
        App.setRoot("login");
    }

    public void checkInput(){

    }

    @FXML
    public void confirmBtnClick(){
        //TODO content check.
        RegisteryCheckMessage result = registerService.register(usernameTxt.getText(), emailTxt.getText(), passwordTxt.getText());

        if(!result.getUsernameIsUnique() && !result.getEmailIsUnique()){
            emailTxt.setStyle("-fx-text-fill: red; -fx-border-color: red");
            usernameTxt.setStyle("-fx-text-fill: red; -fx-border-color: red");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Username and email already in use. chose another one.");
            alert.show();
        }

        else if(!result.getEmailIsUnique()){
            emailTxt.setStyle("-fx-text-fill: red; -fx-border-color: red");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Email already in use. chose another one.");
            alert.show();
        }
        else if(!result.getUsernameIsUnique()){
            usernameTxt.setStyle("-fx-text-fill: red; -fx-border-color: red");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Username already in use. chose another one.");
            alert.show();
        }
    }
}
