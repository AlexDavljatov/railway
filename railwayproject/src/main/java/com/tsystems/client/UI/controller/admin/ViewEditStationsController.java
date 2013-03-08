package com.tsystems.client.UI.controller.admin;

import com.tsystems.client.MyClientImpl;
import com.tsystems.client.UI.App;
import com.tsystems.client.UI.model.StationViewClient;
import com.tsystems.common.model.CommonModel;
import com.tsystems.common.model.Station;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 2/26/13
 * Time: 3:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class ViewEditStationsController implements Initializable {

    private static final Logger log = LoggerFactory.getLogger(ViewTrainsController.class);
    //    @FXML
    //    private Button button;

    @FXML
    private TableView<StationViewClient> tableView;

    @FXML
    private TextField name;

    @FXML
    private Label errorMessage;
    //    @FXML
    //    private TextField email;
    //    @FXML
    //    private TextField password;
    //    @FXML
    //    private TextField birthdayDate;


    ObservableList<StationViewClient> data = FXCollections.observableArrayList();
    //    ObservableList<Person> dbData;

    //    FXMLTableViewController() {
    //////        data = tableView.getItems();
    //        dbData.add(new Person("asd", "asd", "asd"));
    //////        for (Person person : dbData) data.add(person);
    //    }

    @FXML
    protected void addStation(ActionEvent event) throws IOException, ClassNotFoundException {
        data = tableView.getItems();
        //TODO:DONE add station to DB
        //TODO: check, whether the result is OK
        if (MyClientImpl.getInstance().addStationDB(new Station(name.getText()))) {
            data.add(new StationViewClient(name.getText()));
            name.setText("");
        } else {
            errorMessage.setText("Station name field is invalid");
        }
        //        emailField.setText("");
    }

    @FXML
    protected void goBack() throws IOException, ClassNotFoundException {
        if (MyClientImpl.getInstance().isAdmin()) App.getInstance().adminLogin();
        else App.getInstance().userLogin();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        log.debug("ViewTrainsController.initialize()");
        try {
            List<CommonModel> serverResponce = MyClientImpl.getInstance().getStation();
//            log.debug("ViewTrainsController.initialize() serverResponce : " + serverResponce);
            for (CommonModel station : serverResponce) {
                data.add(new StationViewClient((Station) station));
            }
            tableView.setItems(data);
        } catch (IOException e) {
            log.error("ViewTrainsController.initialize() I/O exception" + e.getMessage());
        } catch (ClassNotFoundException e) {
            log.error("ViewTrainsController.initialize() ClassNotFound exception" + e.getMessage());
        }
        //button.setDisable(true);
    }
}
