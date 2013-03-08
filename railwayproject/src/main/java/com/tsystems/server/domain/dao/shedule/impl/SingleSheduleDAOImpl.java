package com.tsystems.server.domain.dao.shedule.impl;

import com.tsystems.server.domain.dao.shedule.SingleSheduleDAO;
import com.tsystems.server.domain.entity.Passenger;
import com.tsystems.server.domain.entity.Shedule;
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
 * Date: 3/3/13
 * Time: 3:59 AM
 * To change this template use File | Settings | File Templates.
 */
public class SingleSheduleDAOImpl implements SingleSheduleDAO {

    EntityManager em;

    private static final Logger log = LoggerFactory.getLogger(CommandImpl.class);

    private static SingleSheduleDAOImpl instance;

    public static synchronized SingleSheduleDAOImpl getInstance() {
        //        log.debug("" + ( == null));
        if (instance == null) {
            instance = new SingleSheduleDAOImpl();
        }
        return instance;
    }

    public static synchronized SingleSheduleDAOImpl getInstance(EntityManager em) {
        //        log.debug("" + ( == null));
        if (instance == null) {
            instance = new SingleSheduleDAOImpl(em);
        }
        return instance;
    }

    public SingleSheduleDAOImpl(EntityManager em) {
        this.em = em;
    }

    public SingleSheduleDAOImpl() {
    }


    @Override
    public Shedule getElement(Object o) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean addElement(Shedule element) {
        return true;
    }

    @Override
    public void updateElement(Shedule element) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Shedule> getAllElements() {
        log.debug("SingleSheduleDAOImpl getAllElements()");
//        log.debug("SingleSheduleDAOImpl getAllElements() " + (em == null));
        EntityTransaction et = em.getTransaction();
        et.begin();
        Query query = em.createQuery("Select shedule from Shedule shedule");
        et.commit();
        log.debug("getAllElements() results" + query.getResultList());
        return (List<Shedule>) query.getResultList();
    }

    public List<Shedule> getSheduleByStation(String stationName) {
        log.debug("SingleSheduleDAOImpl getSheduleByStation()");
        EntityTransaction et = em.getTransaction();
        et.begin();
        Query query = em.createNamedQuery("getSheduleByStation");
        TypedQuery<Station> stationQuery = em.createNamedQuery("getStationByName", Station.class);
        stationQuery.setParameter("stationName", stationName);
//        TypedQuery<Train> trainQuery = em.createNamedQuery("getTrainByNumber", Train.class);
//        trainQuery.setParameter("trainNumber", );
        log.debug("getSheduleByStation() station" + stationQuery.getResultList());
        query.setParameter("stationId", stationQuery.getSingleResult().getId());
        log.debug("getSheduleByStation() results" + query.getResultList());
        et.commit();
        return (List<Shedule>) query.getResultList();
    }
}
