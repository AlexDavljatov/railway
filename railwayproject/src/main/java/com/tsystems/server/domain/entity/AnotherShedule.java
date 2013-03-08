package com.tsystems.server.domain.entity;

import com.tsystems.server.domain.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 3/4/13
 * Time: 3:13 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@NamedQueries({
        @NamedQuery(name="getAllAnotherShedules",
                query = "Select shedule from AnotherShedule shedule"),
        @NamedQuery(name="getTrainByStationUsingAnotherShedule",
                query = "SELECT shedule from AnotherShedule shedule where shedule.station.name = :stationName"),
        @NamedQuery(name="findTrainByTimeBoundaries",
                query = "select shedule.train from AnotherShedule shedule where (shedule.time >= :lowBoundary) and (shedule.time <= :highBoundary)"),
        @NamedQuery(name="getAnotherShedulerByTrainNumber",
                query = "select shedule from AnotherShedule  shedule where shedule.train.number = :trainNumber"),
        @NamedQuery(name="getAnotherShedulerByStationAndTrain",
                query = "select shedule from AnotherShedule shedule where (shedule.train.number= :trainNumber and shedule.station.name= :stationName)")
})
public class AnotherShedule implements Serializable {


    public AnotherShedule() {
    }

    public AnotherShedule(Train train, Station station, Date time) {
        this.train = train;
        this.station = station;
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

    @ManyToOne(cascade = CascadeType.ALL)
    private Train train;

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    private Station station;

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
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
