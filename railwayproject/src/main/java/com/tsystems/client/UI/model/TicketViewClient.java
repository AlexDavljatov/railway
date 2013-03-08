package com.tsystems.client.UI.model;

import com.tsystems.common.model.Ticket;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 3/3/13
 * Time: 11:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class TicketViewClient {
    private final SimpleStringProperty number = new SimpleStringProperty("");
    //    private final SimpleStringProperty ownerEmail = new SimpleStringProperty("");
    private final SimpleStringProperty trainNumber = new SimpleStringProperty("");


    public TicketViewClient() {
        this("", "");
    }

    //     public TicketViewClient(String number, String ownerEmain, String trainNumber){
    public TicketViewClient(String number, String trainNumber) {
        setNumber(number);
//        setOwnerEmail(ownerEmain);
        setTrainNumber(trainNumber);
    }

    public TicketViewClient(Ticket ticket) {
        this(String.valueOf(ticket.getNumber()), String.valueOf(ticket.getTrain().getNumber()));
    }

    public String getNumber() {
        return number.get();
    }

    public void setNumber(String value) {
        number.set(value);
    }
//
//    public String getOwnerEmail() {
//        return number.get();
//    }
//
//    public void setOwnerEmail (String value) {
//            ownerEmail.set(value);
//        }


    public String getTrainNumber() {
        return trainNumber.get();
    }

    public void setTrainNumber(String value) {
        trainNumber.set(value);
    }

}
