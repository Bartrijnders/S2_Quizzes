package org.rijnders;

import javafx.scene.control.Alert;

import java.io.IOException;
import java.sql.SQLException;

public class ExceptionAlert {

    private static ExceptionAlert exceptionAlertInstance;

    private ExceptionAlert() {
    }

    public static ExceptionAlert getInstance() {
        if (exceptionAlertInstance == null) {
            exceptionAlertInstance = new ExceptionAlert();
        }
        return exceptionAlertInstance;
    }

    public Alert newSQLAlert(SQLException exception){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Error: " + exception.getErrorCode());
        alert.setContentText(exception.getMessage());
        return alert;
    }

    public Alert newIOAlert(IOException exception){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Error: " + exception.getCause());
        alert.setContentText(exception.getMessage());
        return alert;
    }
}
