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
import com.tsystems.common.model.LoginPassword;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Login Controller.
 */
public class LoginController implements Initializable {

    private Pattern pattern;
    private Matcher matcher;
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})";


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
        if (
//         (!matcher.matches() ||
                !MyClientImpl.getInstance().doLogin(new LoginPassword(email.getText(), password.getText()))) {
            log.debug("LoginController.processLogin() incorrect login/password");
            errorMessage.setText("Email/password combination is invalid.");
        } else {
            log.debug("LoginController.processLogin() success");
            if (MyClientImpl.getInstance().isAdmin()) App.getInstance().adminLogin();
            else App.getInstance().userLogin();
        }
    }

    @FXML
    protected void processRegister() {
        log.debug("LoginController.processRegister()");
        App.getInstance().userRegister();
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email.getText());
        email.setPromptText("");
        password.setPromptText("");
    }
}
