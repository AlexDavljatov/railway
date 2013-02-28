package com.tsystems.server.Protocol.Command;

import com.tsystems.server.domain.entity.Passenger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 2/28/13
 * Time: 2:46 AM
 * To change this template use File | Settings | File Templates.
 */
public class CommandImpl {

    public static final EntityManagerFactory factory =
            Persistence.createEntityManagerFactory("myapp");


    public boolean registerUser(Passenger passenger) {
        EntityManager em = factory.createEntityManager();
        EntityTransaction trx = em.getTransaction();
        trx.begin();
        //Passenger passenger = new Passenger("Vasya", "Ivanov", false, new Date("02.02.1992"), null);
        //Passenger passenger = new Passenger("Petya", "Ivanov", "aaa@aaa.aaa", "password", false);
        em.persist(passenger);
        trx.commit();
        em.close();
        return true;
    }
}
