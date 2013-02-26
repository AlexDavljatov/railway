package com.tsystems.server.DAO.Entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
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
//@Table(name = "Passenger", schema = "", catalog = "railwaydb")
public class Passenger implements Serializable {

    public Passenger() {
    }

    public Passenger(String name, String surname, boolean administrator, String password, List<Ticket> tickets) {
        this.name = name;
        this.surname = surname;
        this.administrator = administrator;
        this.password = password;
        this.tickets = tickets;
    }

    /*
        public Passenger(String name, String surname, boolean administrator, Date birthday_date, List<Ticket> tickets) {
            this.name = name;
            this.surname = surname;
            this.administrator = administrator;
            this.birthday_date = birthday_date;
            this.tickets = tickets;
        }
        */
    @Id
//    @GeneratedValue
    //@Column(name = "id", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
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

    private String surname;

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    private boolean administrator;

    public boolean isAdministrator() {
        return administrator;
    }

    public void setAdministrator(boolean administrator) {
        this.administrator = administrator;
    }

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private Date birthday_date;

    public Date getBirthday_date() {
        return birthday_date;
    }

    public void setBirthday_date(Date birthday_date) {
        this.birthday_date = birthday_date;
    }

    @OneToMany(mappedBy = "passenger")
    private List<Ticket> tickets;

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @PrePersist
    public void prepareId() {
        if (id == null) {
            id = UUID.randomUUID().toString();
        }
    }
}
