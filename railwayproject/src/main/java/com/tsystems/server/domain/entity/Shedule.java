package com.tsystems.server.domain.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 2/24/13
 * Time: 4:40 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "getAllShedule", query = "Select shedule From Shedule shedule"),
        @NamedQuery(name = "getSheduleByStation", query = "Select shedule from Shedule shedule where shedule.station_id = :stationId")
})
public class Shedule implements Serializable {


    public Shedule() {
    }

    public Shedule(String train_id, String station_id, Date time) {
        this.train_id = train_id;
        this.station_id = station_id;
        this.time = time;
    }

    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "train_id")
    private String train_id;

    public String getTrain_id() {
        return train_id;
    }

    public void setTrain_id(String train_id) {
        this.train_id = train_id;
    }

    @Column(name = "station_id")
    private String station_id = null;

    public String getStation_id() {
        return station_id;
    }

    public void setStation_id(String station_id) {
        this.station_id = station_id;
    }

    private Date time;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @PrePersist
    public void prepareId() {
        if (id == null) {
            id = UUID.randomUUID().toString();
        }
    }
}
