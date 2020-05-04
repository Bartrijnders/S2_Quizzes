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
    @FXML public TextField username_txtfield;
    @FXML public TextField email_txtfield;
    @FXML public PasswordField password_passfield;
    @FXML public PasswordField repassword_passfield;

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
            alert.show();
        }

        else if(!result.getEmailIsUnique()){
            email_txtfield.setStyle("-fx-text-fill: red; -fx-border-color: red");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Email already in use. chose another one.");
            alert.show();
        }
        else if(!result.getUsernameIsUnique()){
            username_txtfield.setStyle("-fx-text-fill: red; -fx-border-color: red");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Username already in use. chose another one.");
            alert.show();
        }
    }
}
