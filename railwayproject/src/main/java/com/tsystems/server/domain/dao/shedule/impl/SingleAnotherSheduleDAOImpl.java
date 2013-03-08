package com.tsystems.server.domain.dao.shedule.impl;

import com.tsystems.server.domain.dao.shedule.SingleAnotherSheduleDAO;
import com.tsystems.server.domain.entity.AnotherShedule;
import com.tsystems.server.domain.entity.Station;
import com.tsystems.server.domain.entity.Train;
import com.tsystems.server.protocol.Command.CommandImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 3/4/13
 * Time: 3:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class SingleAnotherSheduleDAOImpl implements SingleAnotherSheduleDAO {
    EntityManager em;

    private static final Logger log = LoggerFactory.getLogger(CommandImpl.class);

    private static SingleAnotherSheduleDAOImpl instance;

    public static synchronized SingleAnotherSheduleDAOImpl getInstance() {
        //        log.debug("" + ( == null));
        if (instance == null) {
            instance = new SingleAnotherSheduleDAOImpl();
        }
        return instance;
    }

    public static synchronized SingleAnotherSheduleDAOImpl getInstance(EntityManager em) {
        //        log.debug("" + ( == null));
        if (instance == null) {
            instance = new SingleAnotherSheduleDAOImpl(em);
        }
        return instance;
    }

    public SingleAnotherSheduleDAOImpl(EntityManager em) {
        this.em = em;
    }

    public SingleAnotherSheduleDAOImpl() {
    }


    @Override
    public AnotherShedule getElement(Object o) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean addElement(AnotherShedule element) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        TypedQuery<AnotherShedule> query = em.createQuery(
                "select shedule from AnotherShedule shedule where shedule.train = :train and shedule.station = :station and shedule.time = :time", AnotherShedule.class);
        query.setParameter("station", element.getStation());
        query.setParameter("train", element.getTrain());
        query.setParameter("time", element.getTime());
        if (query.getResultList().isEmpty()) {
            em.persist(element);
            et.commit();
            return true;
        }
        et.commit();
        return false;
    }

    @Override
    public void updateElement(AnotherShedule element) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<AnotherShedule> getAllElements() {
        EntityTransaction et = em.getTransaction();
        et.begin();
        TypedQuery<AnotherShedule> query = em.createNamedQuery("getAllAnotherShedules", AnotherShedule.class);
        et.commit();
        return query.getResultList();
    }

    @Override
    public List<AnotherShedule> getSheduleByStationName(String station) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        TypedQuery<AnotherShedule> query = em.createNamedQuery("getTrainByStationUsingAnotherShedule", AnotherShedule.class);
        query.setParameter("stationName", station);
        log.debug("getAnotherSheduleByStationName " + station + " " + query.getResultList());
        et.commit();
        return query.getResultList();
    }

    public List<AnotherShedule> findTrains(String station1, String station2, long time1, long time2) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        TypedQuery<AnotherShedule> query = em.createNamedQuery("getTrainByStationUsingAnotherShedule", AnotherShedule.class);
        List<AnotherShedule> shedules1 = query.setParameter("stationName", station1).getResultList();
//        log.debug("shedule1" + shedules1);
        List<AnotherShedule> shedules2 = query.setParameter("stationName", station2).getResultList();
        et.commit();
        log.debug("shedule1 " + shedules1);
        log.debug("shedule2 " + shedules2);
        List<AnotherShedule> result1 = new LinkedList<AnotherShedule>();
        List<AnotherShedule> result2 = new LinkedList<AnotherShedule>();

        if (shedules1 == null || shedules2 == null) return null;

        Set<Train> result = new TreeSet<Train>();

        for (AnotherShedule shedule : shedules1) {
            log.debug("   " + shedule.getTime().getTime() + " " + shedule.getTime());
            if (shedule.getTime().getTime() >= time1) {
                log.debug("***" + shedule.getTime().getTime() + " " + shedule.getTime());
                result.add(shedule.getTrain());
//                result1.add(shedule);
            }
        }

        log.debug("result1 " + result1);
        List<AnotherShedule> response = new LinkedList<AnotherShedule>();
        for (AnotherShedule shedule : shedules2) {
            log.debug("   " + shedule.getTime().getTime() + " " + shedule.getTime());
            if (shedule.getTime().getTime() <= time2 && result.contains(shedule.getTrain())) {
                log.debug("***" + shedule.getTime().getTime() + " " + shedule.getTime());
//                result2.add(shedule);
                response.add(shedule);
            }
        }
        log.debug("result2 " + result2);

//        List<AnotherShedule> result = new Lin

        result1.retainAll(result2);
        log.debug(" " + result1);
        return response;
//        return result1;
    }

    public synchronized boolean addElement(String station, String trainNumber, long time) {
        EntityTransaction et = em.getTransaction();
        et.begin();

        TypedQuery<Train> trainQuery = em.createNamedQuery("getTrainByNumber", Train.class);
        trainQuery.setParameter("trainNumber", Integer.valueOf(trainNumber));

        TypedQuery<Station> stationQuery = em.createNamedQuery("getStationByName", Station.class);
        stationQuery.setParameter("stationName", station);

        AnotherShedule shedule = new AnotherShedule(trainQuery.getSingleResult(), stationQuery.getSingleResult(), new Date(time));

        TypedQuery<AnotherShedule> query = em.createQuery(
                "select shedule from AnotherShedule shedule where shedule.train = :train and shedule.station = :station and shedule.time = :time", AnotherShedule.class);
        query.setParameter("station", shedule.getStation());
        query.setParameter("train", shedule.getTrain());
        query.setParameter("time", shedule.getTime());
        if (query.getResultList().isEmpty()) {
            em.persist(shedule);
            et.commit();
            return true;
        }
        et.commit();
        return false;
    }
}
