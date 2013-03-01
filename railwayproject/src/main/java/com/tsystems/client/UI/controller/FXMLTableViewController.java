package com.tsystems.client.UI.controller;

import com.tsystems.client.UI.model.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLTableViewController implements Initializable {

    @FXML
    private Button button;

    @FXML
    private TableView<Person> tableView;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField emailField;

    //    ObservableList<Person> data = FXCollections.observableArrayList(new Person("asd", "asd", "asd"), new Person("dsa", "dsa", "dsa"));
    ObservableList<Person> dbData;

//    FXMLTableViewController() {
//////        data = tableView.getItems();
//        dbData.add(new Person("asd", "asd", "asd"));
//////        for (Person person : dbData) data.add(person);
//    }

    @FXML
    protected void addPerson(ActionEvent event) {
//        data = tableView.getItems();
//        data.add(new Person(firstNameField.getText(),
//                lastNameField.getText(),
//                emailField.getText()
//        ));

//        firstNameField.setText("");
//        lastNameField.setText("");
//        emailField.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //button.setDisable(true);
//        tableView.setItems(data);
    }
}
