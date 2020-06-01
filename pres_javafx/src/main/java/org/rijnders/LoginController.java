package org.rijnders;

import com.rijnders.entityinterfaces.User;
import factories.Factory;
import factories.SessionFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import session.SessionAble;
import sevices.LoginService;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {

    @FXML public TextField emailTxtF;
    @FXML public PasswordField passwordPswrdF;


    @FXML
    public void loginBtnClick() {
        try {
            Factory<SessionAble> sessionFactory = new SessionFactory();
            SessionAble session = sessionFactory.Create();
            LoginService loginService = new LoginService();
            User user = loginService.loginWithEmail(emailTxtF.getText(), passwordPswrdF.getText());
            if (user == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Email or password is incorrect. try again.");
                alert.show();
            } else {
                session.getActiveUserService().setUser(user);
                session.getActiveQuestionnaireService().setQCollection(session.getQuestionnaireService().getAllUsersQuestionnaires(session.getActiveUserService().getUser()));
                App.setRoot("home");
                Object cont = App.getController();
                if (cont instanceof HomeController)
                    ((HomeController) cont).setSession(session);
            }


            }
        catch (SQLException exception){
            Alert alert = ExceptionAlert.getInstance().newSQLAlert(exception);
            alert.show();
        }
        catch (IOException exception){
            Alert alert = ExceptionAlert.getInstance().newIOAlert(exception);
            alert.show();
        }
    }

    @FXML
    public void registerBtnClick() throws IOException {
        App.setRoot("register");
    }

}
