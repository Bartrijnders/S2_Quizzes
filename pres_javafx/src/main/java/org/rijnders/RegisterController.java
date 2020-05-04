package org.rijnders;

import javafx.scene.control.Alert;
import messages.RegisteryCheckMessage;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sevices.RegisterService;

import java.io.IOException;

public class RegisterController {

    private final RegisterService registerService;
    @FXML private TextField username_txtfield;
    @FXML private TextField email_txtfield;
    @FXML private PasswordField password_passfield;
    @FXML private PasswordField repassword_passfield;

    public RegisterController() {
        this.registerService = new RegisterService();
    }

    @FXML
    public void cancelBtnClick() throws IOException {
        App.setRoot("login");
    }

    public void confirmBtnClick(){
        //TODO content check.
        RegisteryCheckMessage result = registerService.register(username_txtfield.getText(), email_txtfield.getText(), password_passfield.getText());

        if(!result.getUsernameIsUnique() && !result.getEmailIsUnique()){
            email_txtfield.setStyle("-fx-text-fill: red; -fx-border-color: red");
            username_txtfield.setStyle("-fx-text-fill: red; -fx-border-color: red");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Username and email already in use. chose another one.");
        }

        if(!result.getEmailIsUnique()){
            email_txtfield.setStyle("-fx-text-fill: red; -fx-border-color: red");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Email already in use. chose another one.");
        }
        if(!result.getUsernameIsUnique()){
            username_txtfield.setStyle("-fx-text-fill: red; -fx-border-color: red");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Username already in use. chose another one.");
        }
    }
}
