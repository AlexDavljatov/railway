package com.tsystems.client.UI.controller;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 2/24/13
 * Time: 10:50 PM
 * To change this template use File | Settings | File Templates.
 */

import com.tsystems.client.UI.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Login Controller.
 */
public class LoginController implements Initializable {
    @FXML
    private TextField userId;
    @FXML
    private PasswordField password;
    @FXML
    private Label errorMessage;

    @FXML
    protected void processLogin() {
        if (!App.getInstance().userLogging(userId.getText(), password.getText())) {
            errorMessage.setText("Username/password combination is invalid.");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userId.setPromptText("demo");
        password.setPromptText("demo");
    }
}
