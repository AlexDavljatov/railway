package com.tsystems.server.domain.entity;

import com.tsystems.server.domain.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * User: alex
 * Date: 2/17/13
 * Time: 2:52 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "getStationByName", query =
                "select station from Station station where station.name = :stationName")
})
public class Station implements Serializable {

    public Station() {
    }

    public Station(String name, List<Train> trains) {
        this.name = name;
        this.trains = trains;
    }

    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "Shedule",
            joinColumns = {@JoinColumn(name = "station_id")},
            inverseJoinColumns = {@JoinColumn(name = "train_id")})
    private List<Train> trains;

    //
    @OneToMany(mappedBy = "station")
    private List<AnotherShedule> shedules;

    public List<AnotherShedule> getShedules() {
        return shedules;
    }

    public void setShedules(List<AnotherShedule> shedules) {
        this.shedules = shedules;
    }

    //
    public List<Train> getTrains() {
        return trains;
    }

    public void setTrains(List<Train> trains) {
        this.trains = trains;
    }

    @PrePersist
    public void prepareId() {
        if (id == null) {
            id = UUID.randomUUID().toString();
        }
    }
}
