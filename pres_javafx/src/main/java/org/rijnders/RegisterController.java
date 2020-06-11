package org.rijnders;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import messages.RegistryCheckMessageAble;
import sevices.RegisterService;
import sevices.RegisterServiceAble;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    private final List<Node> nodes = new ArrayList<>();
    @FXML
    public TextField usernameTxt;
    @FXML
    public TextField emailTxt;
    @FXML
    public PasswordField passwordTxt;
    @SuppressWarnings("SpellCheckingInspection")
    @FXML
    public PasswordField repasswordPswdF;
    @FXML
    public Button confirmBtn;
    private RegisterServiceAble registerService;

    public RegisterController() {
        try {
            this.registerService = new RegisterService();
        } catch (SQLException sqlException) {
            Alert alert = ExceptionAlert.getInstance().newSQLAlert(sqlException);
            alert.showAndWait();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nodes.add(usernameTxt);
        nodes.add(emailTxt);
        nodes.add(passwordTxt);
        nodes.add(repasswordPswdF);
        for (Node node : nodes) {
            node.focusedProperty().addListener((observableValue, oldPropertyValue, newPropertyValue) -> {
                if (Boolean.TRUE.equals(newPropertyValue)) {
                    checkFilledIn();
                }
            });
        }

    }

    @FXML
    public void cancelBtnClick() throws IOException {
        App.setRoot(FileNameHandler.LOGIN);
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
    public void confirmBtnClick() {
        try {
            RegistryCheckMessageAble result = registerService.register(usernameTxt.getText(), emailTxt.getText(), passwordTxt.getText());

            String style = "-fx-text-fill: red; -fx-border-color: #ff0000";

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
                a.showAndWait();
                App.setRoot(FileNameHandler.LOGIN);
            }
        } catch (SQLException exception) {
            Alert alert = ExceptionAlert.getInstance().newSQLAlert(exception);
            alert.showAndWait();
        } catch (IOException e) {
            Alert alert = ExceptionAlert.getInstance().newIOAlert(e);
            alert.showAndWait();
        }
    }
}
