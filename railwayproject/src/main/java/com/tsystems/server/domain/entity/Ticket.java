package com.tsystems.server.domain.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * User: alex
 * Date: 2/17/13
 * Time: 2:53 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Ticket implements Serializable {
    public Ticket() {
    }

    @Version
    protected int version;

    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    private Passenger passenger;

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }


    @ManyToOne(cascade = CascadeType.ALL)
    private Train train;


    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    @PrePersist
    public void prepareId() {
        if (id == null) {
            id = UUID.randomUUID().toString();
        }
    }
}
