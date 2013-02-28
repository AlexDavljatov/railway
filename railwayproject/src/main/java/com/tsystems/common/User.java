package com.tsystems.common;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 2/27/13
 * Time: 12:12 AM
 * To change this template use File | Settings | File Templates.
 */

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Data object for a User
 */
public class User implements Serializable {
    private static final Map<String, User> USERS = new HashMap<String, User>();

    private String id;
    private String email;
    private String name;
    private String surName;
    private String password;

    //private String phone;
    private boolean subscribed;
    //private String address;

    private User(String id) {
        this.id = id;
    }

    public static User of(String id) {
        User user = USERS.get(id);
        if (user == null) {
            user = new User(id);
            USERS.put(id, user);
        }
        return user;
    }

    public User(String email, String name, String surName, String password) {
//        this.id = id;
        this.email = email;
        this.name = name;
        this.surName = surName;
        this.password = password;
    }

    public String getId() {
        return id;
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
    public boolean isSubscribed() {
        return subscribed;
    }

    /**
     * @param subscribed the subscribed to set
     */
    public void setSubscribed(boolean subscribed) {
        this.subscribed = subscribed;
    }

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