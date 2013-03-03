//package com.tsystems.client.UI.controller;
//
///**
// * Created with IntelliJ IDEA.
// * User: alex
// * Date: 2/26/13
// * Time: 3:09 PM
// * To change this template use File | Settings | File Templates.
// */
//
//import com.tsystems.client.MyClientImpl;
//import com.tsystems.client.UI.App;
//import com.tsystems.client.UI.model.SheduleViewClient;
//import com.tsystems.client.UI.model.StationViewClient;
//import com.tsystems.client.UI.model.TrainViewClient;
//import com.tsystems.common.model.CommonModel;
//import com.tsystems.common.model.Shedule;
//import com.tsystems.common.model.Station;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.ComboBox;
//import javafx.scene.control.TableView;
//import javafx.scene.layout.GridPane;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.IOException;
//import java.net.URL;
//import java.util.List;
//import java.util.ResourceBundle;
//
///**
// * Login Controller.
// */
//public class ViewSheduleController implements Initializable {
//
//    private static final Logger log = LoggerFactory.getLogger(ViewSheduleController.class);
//
//    @FXML
//    private GridPane gp = new GridPane();
//
//    @FXML
//    private final ComboBox comboBox = new ComboBox();
//
////    @FXML
////    private TableView<SheduleViewClient> tableView;
//
//    @FXML
//    private TableView<TrainViewClient> tableView;
//
//
//    ObservableList<String> dataStations = FXCollections.observableArrayList();
//    ObservableList<SheduleViewClient> dataShedule = FXCollections.observableArrayList();
//    ObservableList<TrainViewClient> data = FXCollections.observableArrayList();
//
//    @FXML
//    protected void findTrains() throws IOException, ClassNotFoundException {
//        //    App.getInstance().viewSheduleByStation((String) comboBox.getValue());
//        log.debug("ViewSheduleController findTrains");
//        try {
//            List<CommonModel> serverResponce = MyClientImpl.getInstance().getTrains(MyClientImpl.getInstance().getLp());
//            log.debug("ViewSheduleController  serverResponce : " + serverResponce);
//            for (CommonModel shedule : serverResponce) {
////                dataShedule.add(new SheduleViewClient(String.valueOf(((Shedule) shedule).getTrainNumber()), ((Shedule) shedule).getTime().toString()));
//            data.add(new TrainViewClient(((Shedule) shedule).getTrainNumber(), "0"));
//            }
////            tableView.setItems(dataShedule);
//            tableView.setItems(data);
//
//        } catch (IOException e) {
//            log.error("ViewSheduleController I/O exception" + e.getMessage());
//        } catch (ClassNotFoundException e) {
//            log.error("ViewSheduleController  ClassNotFound exception" + e.getMessage());
//        }
//    }
//
//
//    @FXML
//    protected void goBack() throws IOException, ClassNotFoundException {
//        if (MyClientImpl.getInstance().isAdmin()) App.getInstance().adminLogin();
//        else App.getInstance().userLogin();
//    }
//
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//
////        comboBox = new ComboBox(options);
////        comboBox.setValue("Choose option");
////        tableView.setItems(dataShedule);
//        tableView.setItems(data);
//        log.debug("ViewSheduleController.initialize()");
//        try {
//            List<CommonModel> serverResponce = MyClientImpl.getInstance().getStation(MyClientImpl.getInstance().getLp());
//            log.debug("ViewSheduleController.initialize() serverResponce : " + serverResponce);
//            for (CommonModel station : serverResponce) {
//                dataStations.add(new StationViewClient((Station) station).getName());
//            }
//            comboBox.setItems(dataStations);
//        } catch (IOException e) {
//            log.error("ViewSheduleController.initialize() I/O exception" + e.getMessage());
//        } catch (ClassNotFoundException e) {
//            log.error("ViewSheduleController.initialize() ClassNotFound exception" + e.getMessage());
//        }
//    }
//}

package com.tsystems.client.UI.controller;

import com.tsystems.client.MyClientImpl;
import com.tsystems.client.UI.App;
import com.tsystems.client.UI.model.SheduleViewClient;
import com.tsystems.common.model.CommonModel;
import com.tsystems.common.model.Shedule;
import com.tsystems.common.model.Station;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
 * Date: 2/26/13
 * Time: 3:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class ViewSheduleController implements Initializable {

    private static final Logger log = LoggerFactory.getLogger(ViewSheduleController.class);
    //    @FXML
    //    private Button button;
    @FXML
    private GridPane gp;

    @FXML
    private TableView<SheduleViewClient> tableView;
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

    ObservableList<SheduleViewClient> data = FXCollections.observableArrayList();
    ObservableList<String> dataStations = FXCollections.observableArrayList("Hi", "Hello", "Okay");
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
        List<CommonModel> serverResponce = MyClientImpl.getInstance().getSheduleByStation((String) combo.getValue());
        for (CommonModel train : serverResponce) {
            //                data.add(new SheduleViewClient("" + ((Train) train).getNumber(), ""+ ((Train) train).getSitsNumber()));
            data.add(new SheduleViewClient((Shedule) train));
        }
        //

        tableView.setItems(data);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        log.debug("ViewTrainsController.initialize()");
        try {
            List<CommonModel> serverResponce = MyClientImpl.getInstance().getStation(MyClientImpl.getInstance().getLp());
//            log.debug("ViewTrainsController.initialize() serverResponce : " + serverResponce);
            for (CommonModel train : serverResponce) {
//                data.add(new SheduleViewClient("" + ((Train) train).getNumber(), ""+ ((Train) train).getSitsNumber()));
                dataStations.add(((Station) train).getName());
            }
//            Saint-Petersburg
            tableView.setItems(data);
            combo.setItems(dataStations);
            gp.add(combo, 0, 1);
        } catch (IOException e) {
            log.error("ViewTrainsController.initialize() I/O exception" + e.getMessage());
        } catch (ClassNotFoundException e) {
            log.error("ViewTrainsController.initialize() ClassNotFound exception" + e.getMessage());
        }
        //button.setDisable(true);
    }
}

