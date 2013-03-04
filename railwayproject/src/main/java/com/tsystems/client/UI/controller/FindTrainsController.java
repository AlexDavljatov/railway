package com.tsystems.client.UI.controller;

import com.tsystems.client.MyClientImpl;
import com.tsystems.client.UI.App;
import com.tsystems.client.UI.Calendar.SimpleCalendar;
import com.tsystems.client.UI.model.SheduleViewClient;
import com.tsystems.common.model.CommonModel;
import com.tsystems.common.model.Station;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
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
 * Time: 3:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class FindTrainsController implements Initializable {
    private static final Logger log = LoggerFactory.getLogger(FindTrainsController.class);
    //    @FXML
    //    private Button button;
    @FXML
    private GridPane gp;

    @FXML
    private TableView<SheduleViewClient> tableView;

    //    Choose Date and time
    @FXML
    private final ComboBox comboA = new ComboBox();
    @FXML
    private final ComboBox comboB = new ComboBox();
    @FXML
    private final ComboBox hoursA = new ComboBox();
    @FXML
    private final ComboBox hoursB = new ComboBox();
    @FXML
    private final ComboBox minutesA = new ComboBox();
    @FXML
    private final ComboBox minutesB = new ComboBox();
    @FXML
    private HBox hBoxA = new HBox(150);
    @FXML
    private HBox hBoxB = new HBox(150);
    @FXML

    private TextField dateFieldA;
    @FXML
    private TextField dateFieldB;
    @FXML
    private Label errorMessage;
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
    ObservableList<String> dataStationsA = FXCollections.observableArrayList();
    ObservableList<String> hours = FXCollections.observableArrayList();
    ObservableList<String> minutes = FXCollections.observableArrayList();
//    ObservableList<String> dataStationsB = FXCollections.observableArrayList();
    //    ObservableList<Person> dbData;

    //    FXMLTableViewController() {
    //////        data = tableView.getItems();
    //        dbData.add(new Person("asd", "asd", "asd"));
    //////        for (Person person : dbData) data.add(person);
    //    }

    @FXML
    protected void findTrains() throws IOException, ClassNotFoundException {
        tableView.getItems().clear();
        log.debug("A: " + dateFieldA.getText() + String.valueOf(hoursA.getValue()) + " " + String.valueOf(minutesA.getValue()));
        log.debug("B: " + dateFieldB.getText() + String.valueOf(hoursB.getValue()) + " " + String.valueOf(minutesB.getValue()));
//        log.debug(dateFieldA.getText() + " " + String.valueOf(hoursA.getValue()) + ":" + String.valueOf(minutesA.getValue()) + ":" + "00");
        log.debug("" + new Date(dateFieldA.getText() + " " + String.valueOf(hoursA.getValue()) + ":" + String.valueOf(minutesA.getValue()) + ":" + "00").getTime());
        log.debug("" + new Date(dateFieldB.getText() + " " + String.valueOf(hoursB.getValue()) + ":" + String.valueOf(minutesB.getValue()) + ":" + "00").getTime());
        MyClientImpl.getInstance().findTrains(String.valueOf(comboA.getValue()), String.valueOf(comboB.getValue()), new Date(dateFieldA.getText() + " " + String.valueOf(hoursA.getValue()) + ":" + String.valueOf(minutesA.getValue()) + ":" + "00").getTime(),
                new Date(dateFieldB.getText() + " " + String.valueOf(hoursB.getValue()) + ":" + String.valueOf(minutesB.getValue()) + ":" + "00").getTime());
//
//          List<CommonModel> serverResponce = MyClientImpl.getInstance().getSheduleByStation((String) combo.getValue());
//        List<CommonModel> serverResponce = MyClientImpl.getInstance().getAnotherSheduleByStation((String) comboA.getValue());
//        for (CommonModel train : serverResponce) {
//            data.add(new SheduleViewClient((Shedule) train));
//        }
//        tableView.setItems(data);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        log.debug("FindTrainsController.initialize()");
        for (int i = 0; i < 60; i++) {
            if (i < 10) minutes.add("0" + i);
            else minutes.add(String.valueOf(i));
        }
        for (int i = 0; i < 24; i++) {
            if (i < 10) hours.add("0" + i);
            else hours.add(String.valueOf(i));
        }
        hoursA.setItems(hours);
        hoursB.setItems(hours);
        minutesA.setItems(minutes);
        minutesB.setItems(minutes);

        try {
            List<CommonModel> serverResponce = MyClientImpl.getInstance().getStation();
            //            log.debug("ViewTrainsController.initialize() serverResponce : " + serverResponce);
            for (CommonModel train : serverResponce) {
                //                data.add(new SheduleViewClient("" + ((Train) train).getNumber(), ""+ ((Train) train).getSitsNumber()));
                dataStationsA.add(((Station) train).getName());
            }
            //            Saint-Petersburg
            tableView.setItems(data);
            comboA.setItems(dataStationsA);
            comboB.setItems(dataStationsA);

//            Init datepickers
//            FROM
            dateFieldA.setEditable(false);
            dateFieldA.setDisable(true);
            SimpleCalendar simpleCalenderA = new SimpleCalendar();
            simpleCalenderA.dateProperty().addListener(new ChangeListener<Date>() {
                @Override
                public void changed(ObservableValue<? extends Date> ov,
                                    Date oldDate, Date newDate) {
                    dateFieldA.setText((new SimpleDateFormat("dd/MM/yyyy")).format(newDate));

                }
            });
            log.debug("date1 = " + dateFieldA.getText());
            hBoxA.getChildren().addAll(comboA, dateFieldA, simpleCalenderA, hoursA, minutesA);

//          WHERE
            dateFieldB.setEditable(false);
            dateFieldB.setDisable(true);
            SimpleCalendar simpleCalenderB = new SimpleCalendar();
            simpleCalenderB.dateProperty().addListener(new ChangeListener<Date>() {
                @Override
                public void changed(ObservableValue<? extends Date> ov,
                                    Date oldDate, Date newDate) {
                    dateFieldB.setText((new SimpleDateFormat("dd/MM/yyyy")).format(newDate));

                }
            });
            log.debug("date2 = " + dateFieldB.getText());
            hBoxB.getChildren().addAll(comboB, dateFieldB, simpleCalenderB, hoursB, minutesB);
//            Close datepickers initiation

//            gp.add(comboA, 0, 1);
//            gp.add(comboB, 1, 1);


        } catch (IOException e) {
            log.error("ViewTrainsController.initialize() I/O exception" + e.getMessage());
        } catch (ClassNotFoundException e) {
            log.error("ViewTrainsController.initialize() ClassNotFound exception" + e.getMessage());
        }
        //button.setDisable(true);
    }
}
