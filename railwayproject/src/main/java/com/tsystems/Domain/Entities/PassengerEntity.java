package com.tsystems.Domain.Entities;

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
@javax.persistence.Table(name = "passenger", schema = "", catalog = "railwaydb")
@Entity
public class PassengerEntity {
    private int id;

    @javax.persistence.Column(name = "id", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String name;

    @javax.persistence.Column(name = "name", nullable = false, insertable = true, updatable = true, length = 50, precision = 0)
    @Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String surname;

    @javax.persistence.Column(name = "surname", nullable = false, insertable = true, updatable = true, length = 50, precision = 0)
    @Basic
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    private Date birthdayDate;

    @javax.persistence.Column(name = "birthday_date", nullable = true, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public Date getBirthdayDate() {
        return birthdayDate;
    }

    public void setBirthdayDate(Date birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PassengerEntity that = (PassengerEntity) o;

        if (id != that.id) return false;
        if (birthdayDate != null ? !birthdayDate.equals(that.birthdayDate) : that.birthdayDate != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;

        return true;
    }

}
