package org.rijnders;

import javafx.scene.control.Alert;
import session.SessionAble;

import java.io.IOException;
import java.sql.SQLException;

public class SceneSwitcher implements SceneSwitchAble {
    @Override
    public void switchTo(SessionAble session, String fxmlFileName) {
        try {
            App.setRoot(fxmlFileName);
            SetSessionAble cont = (SetSessionAble) App.getController();
            assert cont != null;
            cont.setSession(session);

        } catch (IOException e) {
            Alert alert = ExceptionAlert.getInstance().newIOAlert(e);
            alert.show();
        } catch (SQLException e) {
            Alert alert = ExceptionAlert.getInstance().newSQLAlert(e);
            alert.show();
        }

    }
}
