package com.tsystems;

import com.tsystems.server.domain.entity.Passenger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Hello world!
 */

public class Main {
    public static final EntityManagerFactory factory =
            Persistence.createEntityManagerFactory("myapp");

    public static void main(String[] args) {
    }

    public void addPassenger(Passenger passenger) {
        EntityManager em = factory.createEntityManager();
        EntityTransaction trx = em.getTransaction();
        trx.begin();
        //Passenger passenger = new Passenger("Vasya", "Ivanov", false, new Date("02.02.1992"), null);
        //Passenger passenger = new Passenger("Petya", "Ivanov", "aaa@aaa.aaa", "password", false);
        em.persist(passenger);
        trx.commit();
        em.close();
    }
}