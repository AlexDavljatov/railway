package com.tsystems.client.UI.model;

import com.tsystems.common.model.Station;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 3/2/13
 * Time: 2:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class StationViewClient {
    private final SimpleStringProperty name = new SimpleStringProperty("");

    public StationViewClient(String name) {
        setName(name);
    }

    public StationViewClient(Station station){
        setName(station.getName());
    }

    public String getName() {
        return name.get();
    }

    public void setName(String fName) {
        name.set(fName);
    }
}
