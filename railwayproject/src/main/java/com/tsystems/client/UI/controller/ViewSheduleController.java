package com.tsystems.client.UI.controller;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 2/26/13
 * Time: 3:09 PM
 * To change this template use File | Settings | File Templates.
 */

import com.tsystems.client.MyClientImpl;
import com.tsystems.client.UI.App;
import com.tsystems.client.UI.model.StationViewClient;
import com.tsystems.common.model.CommonModel;
import com.tsystems.common.model.Station;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Login Controller.
 */
public class ViewSheduleController implements Initializable {

    private static final Logger log = LoggerFactory.getLogger(ViewSheduleController.class);

    @FXML
    private GridPane gp = new GridPane();

    @FXML
    private final ComboBox comboBox = new ComboBox();

    ObservableList<String> data = FXCollections.observableArrayList();

    @FXML
    protected void findTrains() {

    }

    @FXML
    protected void goBack() throws IOException, ClassNotFoundException {
        if (MyClientImpl.getInstance().isAdmin()) App.getInstance().adminLogin();
        else App.getInstance().userLogin();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

//        comboBox = new ComboBox(options);
        comboBox.getItems().addAll(
                "Option 4",
                "Option 5",
                "Option 6"
        );
//        comboBox.setValue("Choose option");
        log.debug("ViewSheduleController.initialize()");
        try {
            List<CommonModel> serverResponce = MyClientImpl.getInstance().getStation(MyClientImpl.getInstance().getLp());
            log.debug("ViewSheduleController.initialize() serverResponce : " + serverResponce);
            for (CommonModel station : serverResponce) {
                data.add(new StationViewClient((Station) station).getName());
            }
            comboBox.setItems(data);
        } catch (IOException e) {
            log.error("ViewSheduleController.initialize() I/O exception" + e.getMessage());
        } catch (ClassNotFoundException e) {
            log.error("ViewSheduleController.initialize() ClassNotFound exception" + e.getMessage());
        }
    }
}
