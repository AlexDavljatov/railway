package com.tsystems.common.model;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 3/2/13
 * Time: 2:33 PM
 * To change this template use File | Settings | File Templates.
 */

public class Train implements CommonModel {

    private int number;

    private int sitsNumber;

    public Train() {
    }

    public Train(int number, int sitsNumber) {
        this.number = number;
        this.sitsNumber = sitsNumber;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSitsNumber() {
        return sitsNumber;
    }

    public void setSitsNumber(int sitsNumber) {
        this.sitsNumber = sitsNumber;
    }

}
