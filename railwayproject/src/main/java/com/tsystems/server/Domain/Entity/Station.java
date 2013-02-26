package com.tsystems.server.Domain.Entity;

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
public class Station implements Serializable {

    public Station() {
    }

    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private int name;

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }


    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "shedule",
            joinColumns = {@JoinColumn(name = "station_id")},
            inverseJoinColumns = {@JoinColumn(name = "train_id")})
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
