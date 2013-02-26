package com.tsystems;

import com.tsystems.server.DAO.Entity.Test;

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
        EntityManager em = factory.createEntityManager();
        EntityTransaction trx = em.getTransaction();
        trx.begin();
        //Passenger passenger = new Passenger("Vasya", "Ivanov", false, new Date("02.02.1992"), null);
        //Passenger passenger = new Passenger("Vasya", "Ivanov", false, "password", null);
        Test test = new Test();
        em.persist(test);
        trx.commit();
        em.close();
    }
}