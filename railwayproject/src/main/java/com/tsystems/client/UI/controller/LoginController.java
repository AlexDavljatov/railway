package com.tsystems.client.UI.controller;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 2/24/13
 * Time: 10:50 PM
 * To change this template use File | Settings | File Templates.
 */

import com.tsystems.client.MyClientImpl;
import com.tsystems.client.UI.App;
import com.tsystems.common.LoginPassword;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;

/**
 * Login Controller.
 */
public class LoginController implements Initializable {

    private static final Logger log = LoggerFactory.getLogger(MyClientImpl.class);

    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private Label errorMessage;

    @FXML
    protected void processLogin() throws IOException, ClassNotFoundException {
        log.debug("LoginController.processLogin()");
        /*if (!App.getInstance().userLogging(email.getText(), password.getText())) {
            errorMessage.setText("Email/password combination is invalid.");
        }  else {

        }
        */

        if (!MyClientImpl.getInstance().doLogin(new LoginPassword(email.getText(), password.getText()))) {
            log.debug("LoginController.processLogin() incorrect login/password");
            errorMessage.setText("Email/password combination is invalid.");
        } else {
            log.debug("LoginController.processLogin() success");
            App.getInstance().userLogin();
        }
    }

    @FXML
    protected void processRegister() {
        log.debug("LoginController.processRegister()");
        App.getInstance().userRegister();
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        email.setPromptText("");
        password.setPromptText("");
    }
}
