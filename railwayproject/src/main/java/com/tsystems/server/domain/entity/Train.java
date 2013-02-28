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
 * Time: 2:53 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Train extends BaseEntity {

//    @Version
//    protected int version;

    public int getVersion() {
        return version;
    }

    public Train() {
    }

    /*   @Id
       private String id;
    */
    public String getId() {
        return id;
    }

    /*
        public void setId(String id) {
            this.id = id;
        }
      */
    private int sits_number;

    public int getSits_number() {
        return sits_number;
    }

    public void setSits_number(int sits_number) {
        this.sits_number = sits_number;
    }

    @OneToMany(mappedBy = "train")
    private List<Ticket> tickets;

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @ManyToMany(mappedBy = "trains")
    private List<Station> stations;

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }

    @PrePersist
    public void prepareId() {
        if (id == null) {
            id = UUID.randomUUID().toString();
        }
    }
}
