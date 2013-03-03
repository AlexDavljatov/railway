package com.tsystems.server.domain.entity;

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

    @Version
    protected int version;

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
            joinColumns = {@JoinColumn(name = "stationId")},
            inverseJoinColumns = {@JoinColumn(name = "trainId")})
    private List<Train> trains;

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
