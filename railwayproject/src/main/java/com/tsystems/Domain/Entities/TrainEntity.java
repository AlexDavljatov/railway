package com.tsystems.Domain.Entities;

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
@javax.persistence.Table(name = "train", schema = "", catalog = "railwaydb")
@Entity
public class TrainEntity {
    private int id;

    @javax.persistence.Column(name = "id", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int sitsNumber;

    @javax.persistence.Column(name = "sits_number", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public int getSitsNumber() {
        return sitsNumber;
    }

    public void setSitsNumber(int sitsNumber) {
        this.sitsNumber = sitsNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TrainEntity that = (TrainEntity) o;

        if (id != that.id) return false;
        if (sitsNumber != that.sitsNumber) return false;

        return true;
    }
}

