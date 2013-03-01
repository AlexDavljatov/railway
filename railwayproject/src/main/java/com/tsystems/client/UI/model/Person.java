package com.tsystems.client.UI.model;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 2/26/13
 * Time: 3:32 AM
 * To change this template use File | Settings | File Templates.
 */

import com.tsystems.common.User;
import javafx.beans.property.SimpleStringProperty;

public class Person {

    private final SimpleStringProperty name = new SimpleStringProperty("");
    private final SimpleStringProperty surname = new SimpleStringProperty("");
    private final SimpleStringProperty email = new SimpleStringProperty("");
    private final SimpleStringProperty password = new SimpleStringProperty("");
    private final SimpleStringProperty birthdayDate = new SimpleStringProperty("");


    public Person() {
        this("", "", "", "", "");
    }

    public Person(String name, String surname, String email, String password, String birthdayDate) {
        setName(name);
        setSurname(surname);
        setEmail(email);
        setBirthdayDate(birthdayDate);
        setPassword(password);
    }

    public Person(User user) {
        setName(user.getName());
        setSurname(user.getSurName());
        setEmail(user.getEmail());
        setBirthdayDate(user.getBirthdayDate().toString());
        setPassword(user.getPassword());

    }

    public String getName() {
        return name.get();
    }

    public void setName(String fName) {
        name.set(fName);
    }

    public String getSurname() {
        return surname.get();
    }

    public void setSurname(String fName) {
        surname.set(fName);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String fName) {
        email.set(fName);
    }

    public String getPassword() {
        return password.get();
    }

    public void setPassword(String fName) {
        password.set(fName);
    }

    public String getBirthdayDate() {
        return birthdayDate.get();
    }

    public void setBirthdayDate(String fName) {
        birthdayDate.set(fName);
    }
}