package com.tsystems.server.domain.dao.users;

import com.tsystems.server.domain.entity.Passenger;
import com.tsystems.server.domain.dao.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 2/28/13
 * Time: 2:09 AM
 * To change this template use File | Settings | File Templates.
 */
public interface UserDAO extends BaseDAO<Passenger> {
//    public List<Passenger> getAllUsers();

//    public void addPAssanger(Passenger passenger) {
//    }

    //public Passenger getElement(String s);
    public boolean isAdmin(String email);
}
