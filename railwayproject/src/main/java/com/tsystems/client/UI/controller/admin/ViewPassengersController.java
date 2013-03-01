package com.tsystems.client.UI.controller.admin;

import com.tsystems.client.MyClientImpl;
import com.tsystems.client.UI.model.Person;
import com.tsystems.common.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 2/26/13
 * Time: 3:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class ViewPassengersController implements Initializable {
    private static final Logger log = LoggerFactory.getLogger(ViewPassengersController.class);
//    @FXML
//    private Button button;

    @FXML
    private TableView<Person> tableView;

//    @FXML
//    private TextField name;
//    @FXML
//    private TextField surname;
//    @FXML
//    private TextField email;
//    @FXML
//    private TextField password;
//    @FXML
//    private TextField birthdayDate;


    ObservableList<Person> data = FXCollections.observableArrayList(new Person("y", "y", "y", "y", "y"), new Person("x", "x", "x", "x", "x"));
//    ObservableList<Person> dbData;

//    FXMLTableViewController() {
//////        data = tableView.getItems();
//        dbData.add(new Person("asd", "asd", "asd"));
//////        for (Person person : dbData) data.add(person);
//    }

    @FXML
    protected void addPerson(ActionEvent event) {
        data = tableView.getItems();
        /*data.add(new Person(firstNameField.getText(),
                lastNameField.getText(),
                emailField.getText()
        ));*/

//        firstNameField.setText("");
//        lastNameField.setText("");
//        emailField.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        log.debug("ViewPassangersController.initialize()");
        try {
            List<User> serverResponce = MyClientImpl.getInstance().getUsers(MyClientImpl.getInstance().getLp());
            log.debug("ViewPassangersController.initialize() serverResponce : " + serverResponce);
            for (User user : serverResponce) data.add(new Person(user));
            tableView.setItems(data);
        } catch (IOException e) {
            log.error("ViewPassangersController.initialize() I/O exception" + e.getMessage());
        } catch (ClassNotFoundException e) {
            log.error("ViewPassangersController.initialize() ClassNotFound exception" + e.getMessage());
        }
        //button.setDisable(true);

    }
}
