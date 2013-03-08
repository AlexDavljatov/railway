package com.tsystems.common.others;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 3/1/13
 * Time: 12:13 AM
 * To change this template use File | Settings | File Templates.
 */
public class UserSer implements Serializable {

    private String name;
    private String surName;
    private String email;
    private String password;

    public UserSer() {

    }

    public UserSer(String name, String surName, String email, String password) {
        //        this.id = id;
        this.email = email;
        this.name = name;
        this.surName = surName;
        this.password = password;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the subscribed
     */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
