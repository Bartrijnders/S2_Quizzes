package org.rijnders;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import messages.RegisteryCheckMessage;
import sevices.RegisterService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    private final RegisterService registerService;
    @FXML public TextField usernameTxt;
    @FXML public TextField emailTxt;
    @FXML public PasswordField passwordTxt;
    @FXML public PasswordField repasswordPswdF;
    @FXML public Button confirm_btn;
    List<Node> nodes = new ArrayList<>();

    public RegisterController() {
        this.registerService = new RegisterService();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nodes.add(usernameTxt);
        nodes.add(emailTxt);
        nodes.add(passwordTxt);
        nodes.add(repasswordPswdF);
        for(Node node : nodes){
            node.focusedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldPropertyValue, Boolean newPropertyValue) {
                    if(newPropertyValue) {
                       checkFilledIn();
                    }
                }
            });
        }

    }

    @FXML
    public void cancelBtnClick() throws IOException {
        App.setRoot("login");
    }

    @FXML
    public void checkFilledIn(){
        boolean pass = true;
        for(Node node : nodes){
            if(node instanceof TextField){
                if(((TextField) node).getText().isEmpty()){
                    pass = false;
                }
            }
        }
        if(pass){
            confirm_btn.setDisable(false);
        }
        else{
            confirm_btn.setDisable(true);
        }
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
        else{
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText("new account created. please log in");
            a.show();
            try{
                App.setRoot("login");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
