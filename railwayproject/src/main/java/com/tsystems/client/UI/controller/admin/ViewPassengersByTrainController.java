package com.tsystems.client.UI.controller.admin;

import com.tsystems.client.MyClientImpl;
import com.tsystems.client.UI.App;
import com.tsystems.client.UI.model.Person;
import com.tsystems.common.model.CommonModel;
import com.tsystems.common.model.Train;
import com.tsystems.common.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 3/4/13
 * Time: 2:39 AM
 * To change this template use File | Settings | File Templates.
 */
public class ViewPassengersByTrainController implements Initializable{

    private static final Logger log = LoggerFactory.getLogger(ViewPassengersByTrainController.class);
    //    @FXML
    //    private Button button;
    @FXML
    private GridPane gp;

    @FXML
    private TableView<Person> tableView;
    @FXML
    private final ComboBox combo = new ComboBox();

    //    @FXML
    //    private TextField email;
    //    @FXML
    //    private TextField password;
    //    @FXML
    //    private TextField birthdayDate;

    @FXML
    protected void goBack() throws IOException, ClassNotFoundException {
        if (MyClientImpl.getInstance().isAdmin()) App.getInstance().adminLogin();
        else App.getInstance().userLogin();
    }

    ObservableList<Person> data = FXCollections.observableArrayList();
    ObservableList<String> dataTrains = FXCollections.observableArrayList();
    //    ObservableList<Person> dbData;

    //    FXMLTableViewController() {
    //////        data = tableView.getItems();
    //        dbData.add(new Person("asd", "asd", "asd"));
    //////        for (Person person : dbData) data.add(person);
    //    }

    @FXML
    //TODO: train number, not id!
    protected void findTrains() throws IOException, ClassNotFoundException {
        tableView.getItems().clear();
        List<CommonModel> serverResponce = MyClientImpl.getInstance().getPassengersByTrainNumber((String) combo.getValue());
        for (CommonModel train : serverResponce) {
            //                data.add(new SheduleViewClient("" + ((Train) train).getNumber(), ""+ ((Train) train).getSitsNumber()));
            data.add(new Person((User) train));
        }
        //

        tableView.setItems(data);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        log.debug("ViewTrainsController.initialize()");
        try {
            List<CommonModel> serverResponce = MyClientImpl.getInstance().getTrains();
//            log.debug("ViewTrainsController.initialize() serverResponce : " + serverResponce);
            for (CommonModel train : serverResponce) {
//                data.add(new SheduleViewClient("" + ((Train) train).getNumber(), ""+ ((Train) train).getSitsNumber()));
                dataTrains.add(String.valueOf(((Train) train).getNumber()));
            }
            tableView.setItems(data);
            combo.setItems(dataTrains);
            gp.add(combo, 0, 1);
        } catch (IOException e) {
            log.error("ViewTrainsController.initialize() I/O exception" + e.getMessage());
        } catch (ClassNotFoundException e) {
            log.error("ViewTrainsController.initialize() ClassNotFound exception" + e.getMessage());
        }
        //button.setDisable(true);
    }
}
