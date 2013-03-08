package com.tsystems.common.model;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 3/2/13
 * Time: 10:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class Station implements CommonModel {

    public Station() {
    }

    public Station(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
