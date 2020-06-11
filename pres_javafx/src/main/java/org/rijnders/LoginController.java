package org.rijnders;

import com.rijnders.entityinterfaces.User;
import factories.SessionFactory;
import factories.SessionFactoryAble;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import session.SessionAble;
import sevices.LoginService;
import sevices.LoginServiceAble;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {

    @FXML public TextField emailTxtF;
    @SuppressWarnings("SpellCheckingInspection")
    @FXML public PasswordField passwordPswrdF;


    @FXML
    public void loginBtnClick() {
        try {
            SessionFactoryAble sessionFactory = new SessionFactory();
            LoginServiceAble loginService = new LoginService();
            User user = loginService.loginWithEmail(emailTxtF.getText(), passwordPswrdF.getText());
            if (user == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Email or password is incorrect. try again.");
                alert.show();
            } else {
                SessionAble session = sessionFactory.create(user);
                SceneSwitchAble sceneSwitchAble = new SceneSwitcher();
                sceneSwitchAble.switchTo(session, FileNameHandler.HOME);
            }
        }
        catch (SQLException exception){
            Alert alert = ExceptionAlert.getInstance().newSQLAlert(exception);
            alert.show();
        }
    }

    @FXML
    public void registerBtnClick() throws IOException {
        App.setRoot("register");
    }

}
