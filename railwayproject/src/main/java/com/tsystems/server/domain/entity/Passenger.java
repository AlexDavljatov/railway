package com.tsystems.server.domain.entity;

import com.tsystems.server.domain.BaseEntity;

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
public class Passenger extends BaseEntity {

    public Passenger() {
    }

    public Passenger(String name, String surname, String email, String password, Date birthdayDate, boolean administrator) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.birthdayDate = birthdayDate;
        this.administrator = administrator;
    }

    /*    @Id
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

    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private Date birthdayDate;

    public Date getBirthdayDate() {
        return birthdayDate;
    }

    public void setBirthdayDate(Date birthdayDate) {
        this.birthdayDate = birthdayDate;
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
