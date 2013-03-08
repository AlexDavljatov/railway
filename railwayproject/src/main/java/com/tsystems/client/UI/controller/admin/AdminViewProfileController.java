package com.tsystems.client.UI.controller.admin;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 3/3/13
 * Time: 12:12 AM
 * To change this template use File | Settings | File Templates.
 */

import com.tsystems.client.MyClientImpl;
import com.tsystems.client.UI.App;
import javafx.fxml.FXML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 3/1/13
 * Time: 2:24 AM
 * To change this template use File | Settings | File Templates.
 */
public class AdminViewProfileController {

    private static final Logger log = LoggerFactory.getLogger(App.class);

    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    protected void viewPassengersByTrainNumber() {
        log.debug("Yes, it was clicked");
        App.getInstance().adminViewPassengersByTrainNumber();
    }

    @FXML
    protected void buyTicket() {
        App.getInstance().buyTicket();
    }

    @FXML
    protected void viewTickets() {
        App.getInstance().viewTickets();
    }

    @FXML
    protected void viewShedule() {
        App.getInstance().viewShedule();
    }

    @FXML
    protected void findTrain() {
        App.getInstance().findTrain();
    }

    @FXML
    protected void logout() throws IOException, ClassNotFoundException {
        App.getInstance().userLogout();
    }

    @FXML
    protected void viewUsers() {
        App.getInstance().adminViewPassengers();
    }

    @FXML
    protected void viewEditTrains() {
        App.getInstance().adminViewEditTrains();
    }

    @FXML
    protected void viewEditStations() {
        App.getInstance().adminViewEditStations();
    }
    @FXML
    protected void addRoutePoint() {
        App.getInstance().adminAddRoutePoint();
    }
}
