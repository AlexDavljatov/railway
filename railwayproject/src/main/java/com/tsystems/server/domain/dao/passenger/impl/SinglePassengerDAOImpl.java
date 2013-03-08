package com.tsystems.server.domain.dao.passenger.impl;

import com.tsystems.server.domain.dao.passenger.SinglePassengerDAO;
import com.tsystems.server.domain.entity.Passenger;
import com.tsystems.server.domain.entity.Ticket;
import com.tsystems.server.protocol.Command.CommandImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 3/3/13
 * Time: 10:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class SinglePassengerDAOImpl implements SinglePassengerDAO {

    EntityManager em;

    private static final Logger log = LoggerFactory.getLogger(CommandImpl.class);

    private static SinglePassengerDAOImpl instance;

    public static synchronized SinglePassengerDAOImpl getInstance() {
        //        log.debug("" + ( == null));
        if (instance == null) {
            instance = new SinglePassengerDAOImpl();
        }
        return instance;
    }

    public static synchronized SinglePassengerDAOImpl getInstance(EntityManager em) {
        //        log.debug("" + ( == null));
        if (instance == null) {
            instance = new SinglePassengerDAOImpl(em);
        }
        return instance;
    }

    public SinglePassengerDAOImpl(EntityManager em) {
        this.em = em;
    }

    public SinglePassengerDAOImpl() {
    }


    @Override
    public Passenger getElement(Object o) {
        String email = (String) o;
        EntityTransaction et = em.getTransaction();
        et.begin();
        TypedQuery<Passenger> query = em.createNamedQuery("getPassengerByEmail", Passenger.class);
        query.setParameter("email", email);
        et.commit();
        if (query.getResultList().isEmpty()) return null;
        return query.getSingleResult();
    }

    @Override
    public synchronized boolean addElement(Passenger element) {
        EntityTransaction et = em.getTransaction();
        et.begin();

        TypedQuery<Passenger> query = em.createNamedQuery("getPassengerByEmail", Passenger.class);
        query.setParameter("email", element.getEmail());
//        log.debug("" + (query.getResultList() == null));
//        log.debug("" + query.getResultList().isEmpty());
////        log.debug("DoublePassenger is " + query.getSingleResult() + " " + (query.getSingleResult() == null));
        log.debug("" + query.getResultList());
        if (query.getResultList().isEmpty()) {
            em.persist(element);
            et.commit();
            return true;
        }
        et.commit();
        return false;
    }

    @Override
    public void updateElement(Passenger element) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Passenger> getAllElements() {
        EntityTransaction et = em.getTransaction();
        et.begin();
        Query query = em.createNamedQuery("getAllPassengers");
        et.commit();
        return (List<Passenger>) query.getResultList();
    }

    public List<Ticket> getPassengerTicketsByEmail(String email) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        Query query = em.createNamedQuery("getPassengerTicketsByEmail");
        query.setParameter("email", email);
        et.commit();
        return (List<Ticket>) query.getResultList();
    }

    public boolean isAdmin(String email) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        Query query = em.createNamedQuery("isPassengerAdministrator");
        query.setParameter("email", email);
        et.commit();
        return ((Boolean)query.getSingleResult());
    }

    public List<Passenger> getPassengersByTrainNumber(String number) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        List<Passenger> result = new LinkedList<Passenger>();
        Query query = em.createNamedQuery("getTrainTicketsByNymber");
        query.setParameter("number", Integer.valueOf(number));
        List<Ticket> queryResult = (List<Ticket>)query.getResultList();
        log.debug("getPassengersByTrainNumber tickets " + number + " " + queryResult);
        for (Ticket ticket: queryResult) {
            log.debug("getPassengersByTrainNumber tickets " + ticket);
            if (ticket != null)
                result.add(ticket.getPassenger());
        }
        log.debug("getPassengersByTrainNumber passengers" + number + " " + result);
        et.commit();
        return result;
    }
}
