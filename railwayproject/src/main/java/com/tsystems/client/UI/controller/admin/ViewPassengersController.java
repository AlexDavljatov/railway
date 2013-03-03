package com.tsystems.client.UI.controller.admin;

import com.tsystems.client.MyClientImpl;
import com.tsystems.client.UI.App;
import com.tsystems.client.UI.Calendar.SimpleCalendar;
import com.tsystems.client.UI.model.Person;
import com.tsystems.common.model.CommonModel;
import com.tsystems.common.model.User;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @FXML
    private TextField name;
    @FXML
    private TextField surname;
    @FXML
    private TextField email;
    @FXML
    private TextField password;
    @FXML
    private TextField birthdayDate;
    @FXML
    private TextField administrator;
    @FXML
    private HBox hBox = new HBox(150);

    ObservableList<Person> data = FXCollections.observableArrayList();
//    ObservableList<Person> dbData;

//    FXMLTableViewController() {
//////        data = tableView.getItems();
//        dbData.add(new Person("asd", "asd", "asd"));
//////        for (Person person : dbData) data.add(person);
//    }

    @FXML
    protected void addPerson(ActionEvent event) {
        data = tableView.getItems();
        //TODO: add passenger to database
        data.add(new Person(name.getText(),
                surname.getText(),
                email.getText(),
                password.getText(),
                new Date(birthdayDate.getText()).toString(),
                administrator.getText().equals("+")));
        //TODO: handle with input data error
//        firstNameField.setText("");
//        lastNameField.setText("");
//        emailField.setText("");
    }

    @FXML
    protected void goBack() throws IOException, ClassNotFoundException {
        if (MyClientImpl.getInstance().isAdmin()) App.getInstance().adminLogin();
        else App.getInstance().userLogin();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        birthdayDate.setEditable(false);
        birthdayDate.setDisable(true);
        SimpleCalendar simpleCalender = new SimpleCalendar();
        simpleCalender.dateProperty().addListener(new ChangeListener<Date>() {
            @Override
            public void changed(ObservableValue<? extends Date> ov,
                                Date oldDate, Date newDate) {
                birthdayDate.setText((new SimpleDateFormat("dd/MM/yyyy")).format(newDate));
            }
        });
        hBox.getChildren().addAll(birthdayDate, simpleCalender);

        log.debug("\nViewPassengersController.initialize()");
        try {
            List<CommonModel> serverResponce = MyClientImpl.getInstance().getUsers(MyClientImpl.getInstance().getLp());
            log.debug("\nViewPassengersController.initialize() serverResponce : " + serverResponce);
            for (CommonModel user : serverResponce) {
//                log.debug("\n" + ((User) user).getBirthdayDate().toString());
                data.add(new Person((User) user));
//                System.out.println(((User)user).getBirthdayDate());
            }
            //log.debug("ViewPassengersController.initialize() data: " + data);
            tableView.setItems(data);
        } catch (IOException e) {
            log.error("ViewPassengersController.initialize() I/O exception" + e.getMessage());
        } catch (ClassNotFoundException e) {
            log.error("ViewPassengersController.initialize() ClassNotFound exception" + e.getMessage());
        }
        //button.setDisable(true);
    }
}
