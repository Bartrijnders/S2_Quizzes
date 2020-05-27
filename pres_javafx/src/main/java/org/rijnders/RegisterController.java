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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    private final RegisterService registerService;
    @FXML public TextField usernameTxt;
    @FXML public TextField emailTxt;
    @FXML public PasswordField passwordTxt;
    @FXML public PasswordField repasswordPswdF;
    @FXML public Button confirmBtn;
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
                    if(Boolean.TRUE.equals(newPropertyValue)) {
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
            if(node instanceof TextField && ((TextField) node).getText().isEmpty()){
                    pass = false;
                    confirmBtn.setDisable(true);

            }
        }
        if(pass){
                confirmBtn.setDisable(false);
        }

    }

    @FXML
    public void confirmBtnClick(){
        try {
            RegisteryCheckMessage result = registerService.register(usernameTxt.getText(), emailTxt.getText(), passwordTxt.getText());

            String style = "-fx-text-fill: red; -fx-border-color: red";

            if (Boolean.FALSE.equals(result.getUsernameIsUnique()) && Boolean.FALSE.equals(result.getEmailIsUnique())) {
                emailTxt.setStyle(style);
                usernameTxt.setStyle(style);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Username and email already in use. chose another one.");
                alert.show();
            } else if (Boolean.FALSE.equals(result.getEmailIsUnique())) {
                emailTxt.setStyle(style);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Email already in use. chose another one.");
                alert.show();
            } else if (Boolean.FALSE.equals(result.getUsernameIsUnique())) {
                usernameTxt.setStyle(style);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Username already in use. chose another one.");
                alert.show();
            } else {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setHeaderText("new account created. please log in");
                a.show();
                try {
                    App.setRoot("login");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        catch (SQLException exception){
            ExceptionAlert.getInstance().newSQLAlert(exception);
        }
    }
}
