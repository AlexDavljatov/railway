package com.tsystems.common.model;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 3/3/13
 * Time: 10:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class Ticket implements CommonModel {

    private int number;

    private User user;

    private Train train;

    public Ticket() {
    }

    public Ticket(int number, User user, Train train) {
        this.number = number;
        this.user = user;
        this.train = train;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }
}
