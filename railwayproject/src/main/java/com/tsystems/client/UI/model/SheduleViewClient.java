package com.tsystems.client.UI.model;

import com.tsystems.common.model.Shedule;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 3/2/13
 * Time: 2:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class SheduleViewClient {

    private final SimpleStringProperty trainNumber = new SimpleStringProperty("");
    private final SimpleStringProperty time = new SimpleStringProperty("");

    public SheduleViewClient(String trainNumber, String time) {
        setTrainNumber(trainNumber);
        setTime(time);
    }

    public SheduleViewClient(Shedule shedule) {
        setTrainNumber(shedule.getTrainNumber());
        setTime(shedule.getTime().toString());
    }

    public String getTrainNumber() {
        return trainNumber.get();
    }


    public void setTrainNumber(String fName) {
        trainNumber.set(fName);
    }

    public String getTime() {
        return time.get();
    }

    public void setTime(String fTime) {
        time.set(fTime);
    }
}