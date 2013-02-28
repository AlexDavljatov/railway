package com.tsystems.client.UI.model;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 2/24/13
 * Time: 10:46 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.HashMap;
import java.util.Map;

/**
 * Data object for a User
 */
public class User {
    private static final Map<String, User> USERS = new HashMap<String, User>();

    private String id;
    private String email;
    private String name;
    private String surName;

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

}