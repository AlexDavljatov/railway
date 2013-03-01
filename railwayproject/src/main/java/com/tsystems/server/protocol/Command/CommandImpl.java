package com.tsystems.server.protocol.Command;

import com.tsystems.common.LoginPassword;
import com.tsystems.server.domain.dao.users.impl.UserDAOImpl;
import com.tsystems.server.domain.entity.Passenger;
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
        log.debug("CommandImpl.login() " + loginPassword.getLogin());
        //TODO: administrators privilegies
        return UserDAOImpl.getInstance().getAllElements();
    }
}
