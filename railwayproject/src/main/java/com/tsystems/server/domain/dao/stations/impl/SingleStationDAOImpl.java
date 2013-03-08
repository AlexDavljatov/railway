package com.tsystems.server.domain.dao.stations.impl;

import com.tsystems.server.domain.dao.stations.SingleStationDAO;
import com.tsystems.server.domain.entity.Passenger;
import com.tsystems.server.domain.entity.Station;
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
 * Time: 10:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class SingleStationDAOImpl implements SingleStationDAO {

    EntityManager em;

    private static final Logger log = LoggerFactory.getLogger(CommandImpl.class);

    private static SingleStationDAOImpl instance;

    public static synchronized SingleStationDAOImpl getInstance() {
        //        log.debug("" + ( == null));
        if (instance == null) {
            instance = new SingleStationDAOImpl();
        }
        return instance;
    }

    public static synchronized SingleStationDAOImpl getInstance(EntityManager em) {
        //        log.debug("" + ( == null));
        if (instance == null) {
            instance = new SingleStationDAOImpl(em);
        }
        return instance;
    }

    public SingleStationDAOImpl(EntityManager em) {
        this.em = em;
    }

    public SingleStationDAOImpl() {
    }

    @Override
    public Station getElement(Object o) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        TypedQuery<Station> query = em.createQuery("Select station from Station station where station.name = :name", Station.class);
        query.setParameter("name", String.valueOf(o));
        et.commit();
        log.debug("getElementByName() " + query.getResultList());
        return query.getSingleResult();
    }

    @Override
    public synchronized boolean addElement(Station element) {
        EntityTransaction et = em.getTransaction();
        log.debug("DoubleStation is " + (em == null));
        et.begin();
        TypedQuery<Station> query = em.createNamedQuery("getStationByName", Station.class);
        query.setParameter("stationName", element.getName());
        log.debug("DoubleStation is " + query.getResultList());
        if (query.getResultList().isEmpty()) {
            em.persist(element);
            et.commit();
            return true;
        }
        et.commit();
        return false;
    }

    @Override
    public void updateElement(Station element) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Station> getAllElements() {
        log.debug("SingleStationDAOImpl getAllElements()");
        log.debug("SingleStationDAOImpl getAllElements() " + (em == null));
        EntityTransaction et = em.getTransaction();
        et.begin();
        Query query = em.createQuery("Select station from Station station");
        et.commit();
        log.debug("getAllElements() results" + query.getResultList());
        return (List<Station>) query.getResultList();
    }
}
