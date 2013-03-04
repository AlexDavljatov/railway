package com.tsystems.client.UI.controller;

import com.tsystems.client.MyClientImpl;
import com.tsystems.client.UI.App;
import com.tsystems.client.UI.model.TicketViewClient;
import com.tsystems.common.model.CommonModel;
import com.tsystems.common.model.Ticket;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
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
 * Time: 3:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class ViewTicketsController implements Initializable {
    private static final Logger log = LoggerFactory.getLogger(ViewTicketsController.class);
    //    @FXML
    //    private Button button;

    @FXML
    private TableView<TicketViewClient> tableView;

    //    @FXML
    //    private TextField email;
    //    @FXML
    //    private TextField password;
    //    @FXML
    //    private TextField birthdayDate;


    ObservableList<TicketViewClient> data = FXCollections.observableArrayList();
    //    ObservableList<Person> dbData;

    //    FXMLTableViewController() {
    //////        data = tableView.getItems();
    //        dbData.add(new Person("asd", "asd", "asd"));
    //////        for (Person person : dbData) data.add(person);
    //    }

    @FXML
    protected void goBack() throws IOException, ClassNotFoundException {
        if (MyClientImpl.getInstance().isAdmin()) App.getInstance().adminLogin();
        else App.getInstance().userLogin();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        log.debug("ViewTicketsController.initialize()");
        try {
            List<CommonModel> serverResponce = MyClientImpl.getInstance().getTickets();
            log.debug("ViewTicketsController.initialize() serverResponce : " + serverResponce);
            for (CommonModel ticket : serverResponce) {
//                log.debug("" + ((Ticket) ticket).getTrain().getNumber());
                data.add(new TicketViewClient("" + ((Ticket) ticket).getNumber(), "" + ((Ticket) ticket).getTrain().getNumber()));
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
