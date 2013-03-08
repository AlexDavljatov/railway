package com.tsystems.client.UI.controller.admin;
// AddRoutePointController
/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 3/5/13
 * Time: 1:04 PM
 * To change this template use File | Settings | File Templates.
 */

import com.tsystems.client.MyClientImpl;
import com.tsystems.client.UI.App;
import com.tsystems.client.UI.Calendar.SimpleCalendar;
import com.tsystems.client.UI.model.SheduleViewClient;
import com.tsystems.common.model.CommonModel;
import com.tsystems.common.model.Shedule;
import com.tsystems.common.model.Station;
import com.tsystems.common.model.Train;
import javafx.animation.FadeTransition;
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
import javafx.util.Duration;
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
public class AddRoutePointController implements Initializable {
    private static final Logger log = LoggerFactory.getLogger(AddRoutePointController.class);
    //    @FXML
    //    private Button button;
    @FXML
    private GridPane gp;

    //    Choose Date and time
    @FXML
    private final ComboBox comboA = new ComboBox();
    @FXML
    private final ComboBox combo = new ComboBox();
    @FXML
    private final ComboBox hoursA = new ComboBox();
    @FXML
    private final ComboBox minutesA = new ComboBox();
    @FXML
    private HBox hBoxA = new HBox(150);
    @FXML
    private TextField dateFieldA;
    @FXML
    private Label errorMessage;
    @FXML
    private Label success;
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
    ObservableList<String> dataTrains = FXCollections.observableArrayList();
//    ObservableList<String> dataStationsB = FXCollections.observableArrayList();
    //    ObservableList<Person> dbData;

    //    FXMLTableViewController() {
    //////        data = tableView.getItems();
    //        dbData.add(new Person("asd", "asd", "asd"));
    //////        for (Person person : dbData) data.add(person);
    //    }

    @FXML
    protected void addRoutePoint() throws IOException, ClassNotFoundException {
        log.debug(dateFieldA.getText());

        log.debug("A: " + comboA.getValue().toString() + dateFieldA.getText() + String.valueOf(hoursA.getValue()) + " " + String.valueOf(minutesA.getValue()));
        //        log.debug(dateFieldA.getText() + " " + String.valueOf(hoursA.getValue()) + ":" + String.valueOf(minutesA.getValue()) + ":" + "00");

        log.debug("" + new Date(dateFieldA.getText() + " " + String.valueOf(hoursA.getValue()) + ":" +
                String.valueOf(minutesA.getValue()) + ":" + "00").toString());


//        List<CommonModel> serverResponce = MyClientImpl.getInstance().findTrains(String.valueOf(comboA.getValue()),
//                String.valueOf(comboB.getValue()), new Date(dateFieldA.getText() + " " + String.valueOf(hoursA.getValue()) +
//                ":" + String.valueOf(minutesA.getValue()) + ":" + "00").getTime(),
//                new Date(dateFieldB.getText() + " " + String.valueOf(hoursB.getValue()) + ":" + String.valueOf(minutesB.getValue()) + ":" + "00").getTime());
        errorMessage.setText("");
        success.setText("");
        if (MyClientImpl.getInstance().addRoutePoint(comboA.getValue().toString(), combo.getValue().toString(),
                new Date(dateFieldA.getText() + " " + String.valueOf(hoursA.getValue()) +
                        ":" + String.valueOf(minutesA.getValue()) + ":" + "00").getTime())) {
            success.setText("The route point was successfully added");
            FadeTransition ft = new FadeTransition(new Duration(3000), success);
            ft.setFromValue(0.0);
            ft.setToValue(1);
            ft.play();
        } else errorMessage.setText("Some fields are incorrect");
        //

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
        minutesA.setItems(minutes);
        try {
            List<CommonModel> serverResponce = MyClientImpl.getInstance().getStation();
            //            log.debug("ViewTrainsController.initialize() serverResponce : " + serverResponce);
            for (CommonModel train : serverResponce) {
                //                data.add(new SheduleViewClient("" + ((Train) train).getNumber(), ""+ ((Train) train).getSitsNumber()));
                dataStationsA.add(((Station) train).getName());
            }
            //            Saint-Petersburg
            comboA.setItems(dataStationsA);

            serverResponce = MyClientImpl.getInstance().getTrains();
            //            log.debug("ViewTrainsController.initialize() serverResponce : " + serverResponce);
            for (CommonModel train : serverResponce) {
                //                data.add(new SheduleViewClient("" + ((Train) train).getNumber(), ""+ ((Train) train).getSitsNumber()));
                dataTrains.add(String.valueOf(((Train) train).getNumber()));
            }
            combo.setItems(dataTrains);

//            Init datepickers
//            FROM
            dateFieldA.setEditable(false);
            dateFieldA.setDisable(true);
            SimpleCalendar simpleCalenderA = new SimpleCalendar();
            simpleCalenderA.dateProperty().addListener(new ChangeListener<Date>() {
                @Override
                public void changed(ObservableValue<? extends Date> ov,
                                    Date oldDate, Date newDate) {
                    dateFieldA.setText((new SimpleDateFormat("MM/dd/yyyy")).format(newDate));

                }
            });
            log.debug("date1 = " + dateFieldA.getText());
            hBoxA.getChildren().addAll(comboA, combo, dateFieldA, simpleCalenderA, hoursA, minutesA);

//
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
