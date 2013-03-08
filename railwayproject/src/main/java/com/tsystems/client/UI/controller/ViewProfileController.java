package com.tsystems.client.UI.controller;

import com.tsystems.client.UI.App;
import javafx.fxml.FXML;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 3/1/13
 * Time: 2:24 AM
 * To change this template use File | Settings | File Templates.
 */
public class ViewProfileController {

    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    protected void viewTickets() {
        App.getInstance().viewTickets();
    }

    @FXML
    protected void buyTicket(){
        App.getInstance().buyTicket();
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
    protected void logout(){
        App.getInstance().userLogout();
    }

//    @FXML
//    protected void viewUsers(){
//        App.getInstance().adminViewPassengers();
//    }
//
//    @FXML
//    protected void viewEditTrains(){
//        App.getInstance().adminViewEditTrains();
//    }
//
//    @FXML
//    protected void viewEditStations(){
//        App.getInstance().adminViewEditStations();
//    }
}
