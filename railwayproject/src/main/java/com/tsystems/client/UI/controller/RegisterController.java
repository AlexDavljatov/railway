package com.tsystems.client.UI.controller;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 2/24/13
 * Time: 10:51 PM
 * To change this template use File | Settings | File Templates.
 */

import com.tsystems.client.MyClientImpl;
import com.tsystems.client.UI.App;
import com.tsystems.common.User;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Java FX FXML Controller.
 */
public class RegisterController implements Initializable {
    @FXML
    private TextField name;
    @FXML
    private TextField surname;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private CheckBox subscribed;
    @FXML
    private Label success;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        User loggedUser = App.getInstance().getLoggedUser();
        name.setText(loggedUser.getId());
        if (loggedUser.getName() != null) {
            name.setText(loggedUser.getName());
        }

        if (loggedUser.getSurName() != null) {
            surname.setText(loggedUser.getSurName());
        }
        if (loggedUser.getEmail() != null) {
            email.setText(loggedUser.getEmail());
        }
        subscribed.setSelected(loggedUser.isSubscribed());
        success.setOpacity(0);
    }

    @FXML
    protected void processLogout() {
        App.getInstance().userLogout();
    }

    @FXML
    protected void processUpdate() throws Exception {
        User loggedUser = App.getInstance().getLoggedUser();
        loggedUser.setEmail(email.getText());
        loggedUser.setName(name.getText());
        loggedUser.setSubscribed(subscribed.isSelected());
        loggedUser.setSurName(surname.getText());
        animateMessage();
        new MyClientImpl().doRegister();
    }

    private void animateMessage() {
        FadeTransition ft = new FadeTransition(new Duration(3000), success);
        ft.setFromValue(0.0);
        ft.setToValue(1);
        ft.play();
    }
}