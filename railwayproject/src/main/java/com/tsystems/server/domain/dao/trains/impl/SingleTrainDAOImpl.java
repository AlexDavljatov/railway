package com.tsystems.server.domain.dao.trains.impl;

import com.tsystems.server.domain.dao.trains.SingleTrainDAO;
import com.tsystems.server.domain.entity.Passenger;
import com.tsystems.server.domain.entity.Train;
import com.tsystems.server.protocol.Command.CommandImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 3/2/13
 * Time: 2:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class SingleTrainDAOImpl implements SingleTrainDAO {

    EntityManager em;

    private static final Logger log = LoggerFactory.getLogger(CommandImpl.class);

    private static SingleTrainDAOImpl instance;

    public static synchronized SingleTrainDAOImpl getInstance() {
//        log.debug("" + ( == null));
        if (instance == null) {
            instance = new SingleTrainDAOImpl();
        }
        return instance;
    }

    public static synchronized SingleTrainDAO getInstance(EntityManager em) {
        //        log.debug("" + ( == null));
        if (instance == null) {
            instance = new SingleTrainDAOImpl(em);
        }
        return instance;
    }

    public SingleTrainDAOImpl() {
    }

    public SingleTrainDAOImpl(EntityManager em) {
        this.em = em;
        log.debug("SingleTrainDAOImpl " + (em == null));
    }

    @Override
    public Train getElement(Object o) {
        String s = (String) o;
        EntityTransaction et = em.getTransaction();
        et.begin();
        TypedQuery<Train> query = em.createNamedQuery("getTrainByNumber", Train.class);
        query.setParameter("trainNumber", s);
        et.commit();
        return query.getSingleResult();
    }

    @Override
    public Train getElementById(String s) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        TypedQuery<Train> query = em.createNamedQuery("getTrainById", Train.class);
        query.setParameter("trainId", s);
        et.commit();
        return query.getSingleResult();
    }

    @Override
    public boolean addElement(Train element) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        TypedQuery<Train> query = em.createNamedQuery("getTrainByNumber", Train.class);
        query.setParameter("trainNumber", element.getNumber());
        log.debug("DoubleTrain is " + query.getResultList() + " ");
        if (query.getResultList().isEmpty()) {
            em.persist(element);
            et.commit();
            return true;
        }
        et.commit();
        return false;
    }

    @Override
    public void updateElement(Train element) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Train> getAllElements() {
        log.debug("SingleTrainDAOImpl getAllElements()");
        log.debug("SingleTrainDAOImpl getAllElements() " + (this.getEm() == null));
        EntityTransaction et = em.getTransaction();
        et.begin();
        Query query = em.createQuery("Select train from Train train");
        et.commit();
        log.debug("getAllElements() results" + query.getResultList());
        return (List<Train>) query.getResultList();
    }

    public EntityManager getEm() {
        return em;
    }
}
