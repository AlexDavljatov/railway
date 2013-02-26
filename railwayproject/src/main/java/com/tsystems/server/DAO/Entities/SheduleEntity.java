package com.tsystems.server.DAO.Entities;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 2/24/13
 * Time: 5:35 PM
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "shedule", schema = "", catalog = "railwaydb")
@Entity
public class SheduleEntity {
    private int id;

    @javax.persistence.Column(name = "id", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int stationId;

    @javax.persistence.Column(name = "station_id", nullable = true, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
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

    private Date time;

    @javax.persistence.Column(name = "time", nullable = true, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SheduleEntity that = (SheduleEntity) o;

        if (id != that.id) return false;
        if (stationId != that.stationId) return false;
        if (trainId != that.trainId) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;

        return true;
    }


}
