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
import com.tsystems.client.UI.Calendar.SimpleCalendar;
import com.tsystems.common.model.User;
import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Java FX FXML Controller.
 */
public class RegisterController implements Initializable {

    private static final Logger log = LoggerFactory.getLogger(MyClientImpl.class);

    @FXML
    private TextField name;
    @FXML
    private TextField surname;
    @FXML
    private TextField email;
    @FXML
    private TextField dateField;
    @FXML
    private PasswordField password;
    @FXML
    private CheckBox subscribed;
    @FXML
    private Label success;
    @FXML
    private Label errorMessage;
    @FXML
    private HBox hBox = new HBox(150);

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        User loggedUser = App.getInstance().getLoggedUser();
//        name.setText(loggedUser.getId());
//        if (loggedUser.getName() != null) {
//            name.setText(loggedUser.getName());
//        }
//
//        if (loggedUser.getSurName() != null) {
//            surname.setText(loggedUser.getSurName());
//        }
//        if (loggedUser.getEmail() != null) {
//            email.setText(loggedUser.getEmail());
//        }
        /*if (loggedUser.getBirthdayDate() != null) {
            dateField.setText(loggedUser.getBirthdayDate().toString());
        }
        */
        subscribed.setSelected(loggedUser.isSubscribed());
        success.setOpacity(0);
//////////////////////////////
        dateField.setEditable(false);
        dateField.setDisable(true);
        SimpleCalendar simpleCalender = new SimpleCalendar();
        simpleCalender.dateProperty().addListener(new ChangeListener<Date>() {
            @Override
            public void changed(ObservableValue<? extends Date> ov,
                                Date oldDate, Date newDate) {
                dateField.setText((new SimpleDateFormat("dd/MM/yyyy")).format(newDate));

            }
        });
        hBox.getChildren().addAll(dateField, simpleCalender);


    }

    @FXML
    protected void processLogout() {
        App.getInstance().userLogout();
        log.debug("RegisterController.processLogin success");
    }

    @FXML
    protected void processUpdate() {

        User registeredUser = App.getInstance().getLoggedUser();
        registeredUser.setName(name.getText());
        registeredUser.setSurName(surname.getText());
        registeredUser.setEmail(email.getText());
        registeredUser.setPassword(password.getText());
        registeredUser.setBirthdayDate(new Date(dateField.getText()));
        Boolean b = false;
        try {
            b = MyClientImpl.getInstance().doRegister(registeredUser);
        } catch (IOException e) {
            log.debug("RegisterController.processUpdate() i/o exception" + e.getMessage());
        } catch (ClassNotFoundException e) {
            log.debug("RegisterController.processUpdate() ClassNotFound exception" + e.getMessage());
        }
//        animateMessage();
        if (b) App.getInstance().userLogout();
        else {
            errorMessage.setText("User with such email is registered already");
        }
    }

    private void animateMessage() {
        FadeTransition ft = new FadeTransition(new Duration(3000), success);
        ft.setFromValue(0.0);
        ft.setToValue(1);
        ft.play();
    }
}