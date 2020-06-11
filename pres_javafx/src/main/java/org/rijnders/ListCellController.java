package org.rijnders;

import com.rijnders.entityinterfaces.Questionnaire;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ListCellController extends ListCell<Questionnaire> {

    @FXML
    public Label nameLbl;
    @FXML
    public AnchorPane anchorPane;

    private FXMLLoader loader;

    @Override
    protected void updateItem(Questionnaire questionnaire, boolean empty) {
        super.updateItem(questionnaire, empty);

        if (empty || questionnaire == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (loader == null) {
                loader = new FXMLLoader(getClass().getResource("listCell.fxml"));
                loader.setController(this);

                try {
                    loader.load();
                } catch (IOException e) {
                    Alert alert = ExceptionAlert.getInstance().newIOAlert(e);
                    alert.showAndWait();
                }
            }
            nameLbl.setText(questionnaire.getName());
            setText(null);
            setGraphic(anchorPane);
        }
    }
}
