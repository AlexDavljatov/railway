package com.tsystems.server.protocol.Command;

import com.tsystems.common.model.LoginPassword;
import com.tsystems.server.domain.dao.shedule.impl.SingleSheduleDAOImpl;
import com.tsystems.server.domain.dao.stations.impl.SingleStationDAOImpl;
import com.tsystems.server.domain.dao.trains.impl.SingleTrainDAOImpl;
import com.tsystems.server.domain.dao.users.impl.UserDAOImpl;
import com.tsystems.server.domain.entity.Passenger;
import com.tsystems.server.domain.entity.Shedule;
import com.tsystems.server.domain.entity.Station;
import com.tsystems.server.domain.entity.Train;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 2/28/13
 * Time: 2:46 AM
 * To change this template use File | Settings | File Templates.
 */
public class CommandImpl {

    //public static final EntityManagerFactory factory =
    //        Persistence.createEntityManagerFactory("myapp");
    private static final Logger log = LoggerFactory.getLogger(CommandImpl.class);

    EntityManager em;

    public CommandImpl(EntityManager em) {
        this.em = em;
    }

    public boolean registerUser(Passenger passenger) {
        log.debug("CommandImpl.registerUser() " + passenger.getEmail());
        //EntityManager em = factory.createEntityManager();
        EntityTransaction trx = em.getTransaction();
        trx.begin();
        //Passenger passenger = new Passenger("Vasya", "Ivanov", false, new Date("02.02.1992"), null);
        //Passenger oldPassenger = new Passenger("Petya", "Ivanov", "aaa@aaa.aaa", "password", false);
        //Train train = new Train();
        em.persist(passenger);
        //em.persist(oldPassenger);
        trx.commit();
        em.close();
        UserDAOImpl.getInstance().addElement(passenger);
        log.debug(UserDAOImpl.getInstance().getAllElements().toString());
        return true;
    }

    //TODO: make a transaction
    public boolean login(LoginPassword loginPassword) {
        log.debug("CommandImpl.login() " + loginPassword.getLogin());
        log.debug(UserDAOImpl.getInstance().getAllElements().toString());
        //if (new UserDAOImpl().get.getUser(loginPassword.getLogin()) == null) return false;
        //new UserDAOImpl().getUser() == null;
        Passenger user = new UserDAOImpl(em).getElement(loginPassword.getLogin());
        return (user != null) &&
                (user.getPassword().equals(loginPassword.getPassword()));
    }

    //TODO: make a transaction
    public List<Passenger> viewUsers(LoginPassword loginPassword) {
        log.debug("CommandImpl.viewUsers() " + loginPassword.getLogin());

        //TODO:DONE administrator's privilegies
        List<Passenger> result = null;
        if (UserDAOImpl.getInstance().isAdmin(loginPassword.getLogin()))
            result = UserDAOImpl.getInstance().getAllElements();
        return result;
    }

    //TODO:DONE make a transaction
    public List<Train> viewAddTrains(LoginPassword loginPassword) {
        log.debug("CommandImpl.viewAddTrains() " + loginPassword.getLogin());

        //TODO:DONE administrator's privilegies
        List<Train> result = null;
        if (UserDAOImpl.getInstance().isAdmin(loginPassword.getLogin()))
            result = SingleTrainDAOImpl.getInstance(em).getAllElements();
        return result;
    }

    //TODO:DONE make a transaction
    public List<Station> viewAddStations(LoginPassword loginPassword) {
        log.debug("CommandImpl.viewAddStations() " + loginPassword.getLogin());

        //TODO:DONE administrator's privilegies
        List<Station> result = null;
//        log.debug("viewAddStation " + UserDAOImpl.getInstance().isAdmin(loginPassword.getLogin()));
//        if (UserDAOImpl.getInstance().isAdmin(loginPassword.getLogin()))
        result = SingleStationDAOImpl.getInstance(em).getAllElements();

        return result;
    }

    //TODO:DONE is administrator
    public boolean isAdmin(LoginPassword loginPassword) {
        log.debug("CommandImpl.isAdmin() " + loginPassword.getLogin());
        return UserDAOImpl.getInstance().isAdmin(loginPassword.getLogin());  //To change body of created methods use File | Settings | File Templates.
    }

    public List<Shedule> getSheduleByStation(String station) {
        log.debug("getSheduleByStation()" + station);
        return SingleSheduleDAOImpl.getInstance(em).getSheduleByStation(station);
    }
}
