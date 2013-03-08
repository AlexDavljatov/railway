package com.tsystems.server.domain.dao.tickets.impl;

import com.tsystems.common.model.LoginPassword;
import com.tsystems.server.domain.dao.tickets.SingleTicketDAO;
import com.tsystems.server.domain.entity.AnotherShedule;
import com.tsystems.server.domain.entity.Passenger;
import com.tsystems.server.domain.entity.Ticket;
import com.tsystems.server.domain.entity.Train;
import com.tsystems.server.protocol.Command.CommandImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 3/3/13
 * Time: 2:55 AM
 * To change this template use File | Settings | File Templates.
 */
public class SingleTicketDAOImpl implements SingleTicketDAO {

    EntityManager em;

    private static final Logger log = LoggerFactory.getLogger(CommandImpl.class);

    private static SingleTicketDAOImpl instance;

    public static synchronized SingleTicketDAOImpl getInstance() {
        //        log.debug("" + ( == null));
        if (instance == null) {
            instance = new SingleTicketDAOImpl();
        }
        return instance;
    }

    public static synchronized SingleTicketDAOImpl getInstance(EntityManager em) {
        //        log.debug("" + ( == null));
        if (instance == null) {
            instance = new SingleTicketDAOImpl(em);
        }
        return instance;
    }

    public SingleTicketDAOImpl(EntityManager em) {
        this.em = em;
    }

    public SingleTicketDAOImpl() {
    }


    @Override
    public Ticket getElement(Object o) {
        Passenger passenger = (Passenger) o;
        EntityTransaction et = em.getTransaction();
        et.begin();
        TypedQuery<Ticket> query = em.createNamedQuery("getPassengerTicketOwner", Ticket.class);
        et.commit();
        return query.getSingleResult();
    }

    @Override
    public boolean addElement(Ticket element) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(element);
        et.commit();
        return true;
    }

    @Override
    public void updateElement(Ticket element) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Ticket> getAllElements() {
        log.debug("SingleTicketDAOImpl getAllElements()");
//        log.debug("SingleTicketDAOImpl getAllElements() " + (em == null));
        EntityTransaction et = em.getTransaction();
        et.begin();
        Query query = em.createQuery("Select ticket from Ticket ticket");

        et.commit();
        log.debug("getAllElements() results" + query.getResultList());
        return (List<Ticket>) query.getResultList();
    }

    public List<Ticket> getTicketsByPassengerEmail(String email) {

        return null;
    }

    public synchronized boolean buyTicket(LoginPassword loginPassword, String trainNumber, String stationName) {
        log.debug("SingleTicketDAOImpl buyTicket() " + trainNumber + " " + loginPassword.getLogin());
        //        log.debug("SingleTicketDAOImpl getAllElements() " + (em == null));
        EntityTransaction et = em.getTransaction();
        et.begin();
//        TypedQuery<Ticket> ticketsByTrainNumberQuery = em.createNamedQuery("getTicketsByTrainNumber", Ticket.class);
//        ticketsByTrainNumberQuery.setParameter("trainNumber", trainNumber);
        TypedQuery<Passenger> passengerQuery = em.createNamedQuery("getPassengerByEmail", Passenger.class);
        passengerQuery.setParameter("email", loginPassword.getLogin());


        // <10 minutes
        TypedQuery<AnotherShedule> sheduleQuery = em.createNamedQuery("getAnotherShedulerByStationAndTrain", AnotherShedule.class);
        sheduleQuery.setParameter("trainNumber", Integer.valueOf(trainNumber));
        sheduleQuery.setParameter("stationName", stationName);
        log.debug("buyTicket " + sheduleQuery.getResultList());

        if (sheduleQuery.getResultList().isEmpty()) {
            et.commit();
            return false;
        }

//        AnotherShedule shedule = sheduleQuery.getSingleResult();
        AnotherShedule shedule = sheduleQuery.getResultList().get(0);
        Date trainArrivalDate = shedule.getTime();
        Train train = shedule.getTrain();
        if (Math.abs(trainArrivalDate.getTime() - System.currentTimeMillis()) < 600000) {
            et.commit();
            return false;
        }
        log.debug("SingleTicketDAOImpl buyTicket() '10 minutes restrioction' is invalid " + trainNumber + " " +
                loginPassword.getLogin() + Math.abs(trainArrivalDate.getTime() - System.currentTimeMillis()));

        // available sits exist
        if (train.getTickets().size() >=
                shedule.getTrain().getSits_number()) {
            et.commit();
            return false;
        }
        log.debug("SingleTicketDAOImpl buyTicket() 'no available sits' is invalid " +
                trainNumber + " " + loginPassword.getLogin() + trainArrivalDate.toString() + train.getTickets().size());

        //TODO: check, whether passenger is registered
        Passenger passenger = passengerQuery.getSingleResult();

        //TODO: no passengers with the same name, surname, birthday Date
        log.debug("SingleTicketDAOImpl buyTicket()" + train.getTickets());
        for (Ticket ticket : train.getTickets()) {
            Passenger rival = ticket.getPassenger();
//            log.debug("Buy ticket " + passenger.getName() + " < - > " + rival.getName() + "  " +
//                    passenger.getSurname() + " < - > " + rival.getSurname() + "  " +
//                    passenger.getBirthdayDate() + " < - > " + rival.getBirthdayDate());
//            log.debug("Buy ticket equals " + passenger.getName().equals(rival.getName()) + " " +
//                    passenger.getSurname().equals(rival.getSurname()) + " " +
//                    passenger.getBirthdayDate().equals(rival.getBirthdayDate()));
//            if (passenger.getName().equals(rival.getName()) && passenger.getSurname().equals(rival.getSurname())
//                    && passenger.getBirthdayDate().equals(rival.getBirthdayDate())) return false;
            if (passenger.equals(rival)) {
                et.commit();
                return false;
            }
        }
        log.debug("SingleTicketDAOImpl buyTicket() 'the same passenger fields is invalid' " + trainNumber + " "
                + loginPassword.getLogin() + trainArrivalDate.toString());
        //TODO: 10 minutes before train's arrival
        Ticket newTicket = new Ticket(100500, passenger, train);
        train.getTickets().add(newTicket);
        em.persist(newTicket);
        et.commit();
        return true;

    }
}