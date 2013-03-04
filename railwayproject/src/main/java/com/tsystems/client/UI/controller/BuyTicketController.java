package com.tsystems.client.UI.controller;

import com.tsystems.client.MyClientImpl;
import com.tsystems.client.UI.App;
import com.tsystems.common.model.CommonModel;
import com.tsystems.common.model.Station;
import com.tsystems.common.model.Train;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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
public class BuyTicketController implements Initializable {
    private static final Logger log = LoggerFactory.getLogger(BuyTicketController.class);
    //    @FXML
    //    private Button button;
    @FXML
    private GridPane gp;

    @FXML
    private final ComboBox comboTrain = new ComboBox();
    @FXML
    private final ComboBox comboStation = new ComboBox();
    //    @FXML
    //    private TextField email;
    //    @FXML
    //    private TextField password;
    //    @FXML
    //    private TextField birthdayDate;
    @FXML
    private HBox hbox = new HBox(150);

    @FXML
    protected void goBack() throws IOException, ClassNotFoundException {
        if (MyClientImpl.getInstance().isAdmin()) App.getInstance().adminLogin();
        else App.getInstance().userLogin();
    }

    ObservableList<String> dataTrains = FXCollections.observableArrayList();
    ObservableList<String> dataStations = FXCollections.observableArrayList();
    //    ObservableList<Person> dbData;

    //    FXMLTableViewController() {
    //////        data = tableView.getItems();
    //        dbData.add(new Person("asd", "asd", "asd"));
    //////        for (Person person : dbData) data.add(person);
    //    }

    @FXML
    //TODO: train number, not id!
    protected void buyTicket() throws IOException, ClassNotFoundException {
        MyClientImpl.getInstance().buyTicket((String) comboTrain.getValue(), (String) comboStation.getValue());
        //
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
            serverResponce = MyClientImpl.getInstance().getStation();
            for (CommonModel train : serverResponce) {
                //                data.add(new SheduleViewClient("" + ((Train) train).getNumber(), ""+ ((Train) train).getSitsNumber()));
                dataStations.add(String.valueOf(((Station) train).getName()));
            }


            comboTrain.setItems(dataTrains);
            comboStation.setItems(dataStations);
            hbox.getChildren().addAll(comboStation, comboTrain);
//            gp.add(comboTrain, 0, 1);
//            gp.add(comboTrain, 1, 1);
        } catch (IOException e) {
            log.error("ViewTrainsController.initialize() I/O exception" + e.getMessage());
        } catch (ClassNotFoundException e) {
            log.error("ViewTrainsController.initialize() ClassNotFound exception" + e.getMessage());
        }
        //button.setDisable(true);
    }
}
