package com.tsystems.server.domain.dao.users.impl;

import com.tsystems.server.domain.dao.users.UserDAO;
import com.tsystems.server.domain.entity.Passenger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 2/28/13
 * Time: 2:11 AM
 * To change this template use File | Settings | File Templates.
 */
public class UserDAOImpl implements UserDAO {
    List<Passenger> users = null;

    private static UserDAOImpl instance;

    public static synchronized UserDAOImpl getInstance() {
        if (instance == null) {
            instance = new UserDAOImpl();
        }
        return instance;
    }

    public UserDAOImpl() {
    }


    public UserDAOImpl(EntityManager em) {
        EntityTransaction trx = em.getTransaction();
        trx.begin();
        Query query = em.createQuery("Select pass from Passenger pass");
        getInstance().users = (List<Passenger>) query.getResultList();
        trx.commit();
        em.close();
//        this.users = users;
    }

    @Override
    public List<Passenger> getAllElements() {
        return users;
    }

    @Override
    public Passenger getElement(Object o) {
        String email = (String) o;
        for (Passenger passenger : getInstance().users)
            if (passenger.getEmail().equals(email)) return passenger;
        return null;
    }

    /*    @Override
        public Passenger getElement(String email) {
            for (Passenger passenger : getInstance().users)
                if (passenger.getEmail().equals(email)) return passenger;
            return null;
        }
    */
    @Override
    public void addElement(Passenger passenger) {
        getInstance().users.add(passenger);
    }

    @Override
    public void removeElement(Passenger element) {
        getInstance().users.remove(element);
    }
}
