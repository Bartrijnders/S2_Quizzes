package org.rijnders;

import javafx.fxml.FXML;

import java.io.IOException;

public class LoginController {

    @FXML
    public void loginBtnClick(){

    }

    @FXML
    public void registerBtnClick() throws IOException {
        App.setRoot("register");
    }

}
