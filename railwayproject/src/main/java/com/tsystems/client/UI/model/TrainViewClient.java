package com.tsystems.client.UI.model;

import com.tsystems.common.model.Train;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 3/2/13
 * Time: 2:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class TrainViewClient {

    private final SimpleStringProperty number = new SimpleStringProperty("");
    private final SimpleStringProperty sitsNumber = new SimpleStringProperty("");

    public TrainViewClient () {
        this("", "");
    }

    public TrainViewClient (String number, String sitsNumber) {
        setNumber(number);
        setSitsNumber(sitsNumber);
    }

    public TrainViewClient (Train train) {
        setNumber(String.valueOf(train.getNumber()));
        setSitsNumber(String.valueOf(train.getSitsNumber()));
    }

    public String getNumber() {
        return number.get();
    }

    public void setNumber(String fName) {
        number.set(fName);
    }

    public String getSitsNumber() {
        return sitsNumber.get();
    }

    public void setSitsNumber(String fName) {
        sitsNumber.set(fName);
    }

}
