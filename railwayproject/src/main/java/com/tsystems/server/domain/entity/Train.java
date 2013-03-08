package com.tsystems.server.domain.entity;

import com.tsystems.server.domain.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
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
@NamedQueries({
        @NamedQuery(name = "getTrainByNumber", query = "SELECT train from Train train where train.number = :trainNumber"),
        @NamedQuery(name = "getTrainById", query = "SELECT train from Train train where train.id = :trainId"),
        @NamedQuery(name = "getTrainTicketsByNymber", query = "Select p.tickets from Train p where p.number = :number"),
})
public class Train extends BaseEntity implements Comparable<Train> {

//    @Version
//    protected int version;

    public int getVersion() {
        return version;
    }

    public Train() {
    }

    public Train(int number, int sits_number, List<Ticket> tickets, List<Station> stations) {
        this.number = number;
        this.sits_number = sits_number;
        this.tickets = tickets;
        this.stations = stations;
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
    private int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

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

    //
    @OneToMany(mappedBy = "train")
    private List<AnotherShedule> shedules;

    public List<AnotherShedule> getShedules() {
        return shedules;
    }

    public void setShedules(List<AnotherShedule> shedules) {
        this.shedules = shedules;
    }

    @Override
    public int compareTo(Train o) {
        return (this.getNumber() - o.getNumber());
    }
}
