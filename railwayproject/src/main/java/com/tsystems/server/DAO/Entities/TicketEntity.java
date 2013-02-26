package com.tsystems.server.DAO.Entities;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 2/24/13
 * Time: 5:35 PM
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "ticket", schema = "", catalog = "railwaydb")
@Entity
public class TicketEntity {
    private int id;

    @javax.persistence.Column(name = "id", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int number;

    @javax.persistence.Column(name = "number", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    private int passengerId;

    @javax.persistence.Column(name = "passenger_id", nullable = true, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    private int trainId;

    @javax.persistence.Column(name = "train_id", nullable = true, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TicketEntity that = (TicketEntity) o;

        if (id != that.id) return false;
        if (number != that.number) return false;
        if (passengerId != that.passengerId) return false;
        if (trainId != that.trainId) return false;

        return true;
    }
}
