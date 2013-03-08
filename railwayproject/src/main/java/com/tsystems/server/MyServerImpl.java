package com.tsystems.server;

import com.tsystems.server.domain.dao.passenger.SinglePassengerDAO;
import com.tsystems.server.domain.dao.passenger.impl.SinglePassengerDAOImpl;
import com.tsystems.server.domain.dao.shedule.SingleAnotherSheduleDAO;
import com.tsystems.server.domain.dao.shedule.SingleSheduleDAO;
import com.tsystems.server.domain.dao.shedule.impl.SingleAnotherSheduleDAOImpl;
import com.tsystems.server.domain.dao.shedule.impl.SingleSheduleDAOImpl;
import com.tsystems.server.domain.dao.stations.SingleStationDAO;
import com.tsystems.server.domain.dao.stations.impl.SingleStationDAOImpl;
import com.tsystems.server.domain.dao.tickets.SingleTicketDAO;
import com.tsystems.server.domain.dao.tickets.impl.SingleTicketDAOImpl;
import com.tsystems.server.domain.dao.trains.SingleTrainDAO;
import com.tsystems.server.domain.dao.trains.impl.SingleTrainDAOImpl;
import com.tsystems.server.domain.dao.users.UserDAO;
import com.tsystems.server.domain.dao.users.impl.UserDAOImpl;
import com.tsystems.server.domain.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.io.IOException;
import java.net.ServerSocket;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 2/19/13
 * Time: 10:37 PM
 * To change this template use File | Settings | File Templates.
 */
//public class MyServerImpl implements MyServer {
public class MyServerImpl {

    final int SERVER_PORT = 9090;
    final String SERVER_IP = "localhost";
    ServerSocket serverSocket;

    private static final Logger log = LoggerFactory.getLogger(MyServerImpl.class);

    public static final EntityManagerFactory factory =
            Persistence.createEntityManagerFactory("myapp");

    private UserDAO userDAO;
    private SingleTrainDAO trainDAO;
    private SingleStationDAO stationDAO;
    private SingleSheduleDAO sheduleDAO;
    private SingleTicketDAO ticketDAO;
    private SinglePassengerDAO passengerDAO;
    private SingleAnotherSheduleDAO anotherSheduleDAO;

    //TODO: remove init method()
    private void init() {
        EntityManager em = factory.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        Train train1 = new Train(50, 600, null, null);
        Train train2 = new Train(150, 800, null, null);
        Train train3 = new Train(424, 1800, null, null);
        Train train4 = new Train(4, 1, null, null);
        Train train5 = new Train(5, 1, null, null);
        Train train6 = new Train(6, 1, null, null);
        Train train7 = new Train(7, 1, null, null);
        Train train8 = new Train(8, 1, null, null);
        Train train9 = new Train(9, 1, null, null);
        Train train10 = new Train(10, 1, null, null);

        em.persist(train1);
        em.persist(train2);
        em.persist(train3);
        em.persist(train4);
        em.persist(train5);
        em.persist(train6);
        em.persist(train7);
        em.persist(train8);
        em.persist(train9);
        em.persist(train10);

        Passenger p1 = new Passenger("aaaa0", "aaaa0", "aaaa0", "aaaa0", new Date(Date.parse("Sat, 12 Aug 1995 13:30:00 GMT")), false);
        Passenger p2 = new Passenger("sudo", "sudo", "sudo@sudo.sudo", "sudo", new Date(Date.parse("Sat, 12 Aug 2003 13:30:00 GMT")), true);
        Passenger p3 = new Passenger("q", "q", "q", "q", new Date(Date.parse("Sat, 12 Aug 1995 13:30:00 GMT")), true);
        em.persist(p1);
        em.persist(p2);
        em.persist(p3);
        Station station1 = new Station("Saint-Petersburg", null);
        Station station2 = new Station("Berlin", null);
        Station station3 = new Station("Frankfurt", null);
        em.persist(station1);
        em.persist(station2);
        em.persist(station3);
//        log.debug("" + train1.getId() + " " + station1.getId());
        Shedule shedule1 = new Shedule(train1.getId(), station1.getId(), new Date(Date.parse("Sat, 9 Apr 2013 13:30:00 GMT")));
        Shedule shedule2 = new Shedule(train2.getId(), station1.getId(), new Date(Date.parse("Sat, 9 Apr 2013 18:30:00 GMT")));
        Shedule shedule3 = new Shedule(train2.getId(), station3.getId(), new Date(Date.parse("Sat, 9 Apr 2013 20:30:00 GMT")));
        em.persist(shedule1);
        em.persist(shedule2);
        em.persist(shedule3);
        Ticket t1 = new Ticket(1, p1, train1);
        Ticket t2 = new Ticket(2, p2, train1);
        Ticket t3 = new Ticket(3, p2, train2);
        Ticket t4 = new Ticket(4, p2, train3);

        em.persist(t1);
        em.persist(t2);
        em.persist(t3);
        em.persist(t4);

//
        AnotherShedule as1 = new AnotherShedule(train1, station1, new Date(Date.parse("Sat, 9 Mar 2013 13:00:00 GMT")));
        AnotherShedule as2 = new AnotherShedule(train2, station1, new Date(Date.parse("Sat, 9 Mar 2013 18:00:00 GMT")));
        AnotherShedule as3 = new AnotherShedule(train2, station3, new Date(Date.parse("Sat, 9 Mar 2013 12:00:00 GMT")));
        AnotherShedule as4 = new AnotherShedule(train3, station1, new Date(System.currentTimeMillis()));
        AnotherShedule as5 = new AnotherShedule(train4, station1, new Date(Date.parse("Sat, 9 Mar 2013 12:00:00 GMT")));
        AnotherShedule as6 = new AnotherShedule(train5, station1, new Date(Date.parse("Sat, 9 Mar 2013 13:00:00 GMT")));
        AnotherShedule as7 = new AnotherShedule(train6, station1, new Date(Date.parse("Sat, 9 Mar 2013 14:00:00 GMT")));
        AnotherShedule as8 = new AnotherShedule(train7, station1, new Date(Date.parse("Sat, 9 Mar 2013 15:00:00 GMT")));
        AnotherShedule as9 = new AnotherShedule(train8, station1, new Date(Date.parse("Sat, 9 Mar 2013 16:00:00 GMT")));
        AnotherShedule as10 = new AnotherShedule(train9, station1, new Date(Date.parse("Sat, 9 Mar 2013 17:00:00 GMT")));
        AnotherShedule as11 = new AnotherShedule(train10, station1, new Date(Date.parse("Sat, 9 Mar 2013 18:00:00 GMT")));

        em.persist(as1);
        em.persist(as2);
        em.persist(as3);
        em.persist(as4);
        em.persist(as5);
        em.persist(as6);
        em.persist(as7);
        em.persist(as8);
        em.persist(as9);
        em.persist(as10);
        em.persist(as11);

//
        et.commit();
        em.close();
    }

//    AnotherShedule as1 = new AnotherShedule(train1, station1, new Date(Date.parse("Sat, 12 Aug 2013 13:30:00 GMT")));
//            AnotherShedule as2 = new AnotherShedule(train2, station1, new Date(Date.parse("Sat, 12 Aug 2013 18:30:00 GMT")));
//            AnotherShedule as3 = new AnotherShedule(train3, station3, new Date(Date.parse("Sat, 12 Aug 2013 20:30:00 GMT")));
//            em.persist(as1);
//            em.persist(as2);
//            em.persist(as3);


    public MyServerImpl() {
        init();
        userDAO = new UserDAOImpl(factory.createEntityManager());
        trainDAO = new SingleTrainDAOImpl(factory.createEntityManager());
        stationDAO = new SingleStationDAOImpl(factory.createEntityManager());
        sheduleDAO = new SingleSheduleDAOImpl(factory.createEntityManager());
        ticketDAO = new SingleTicketDAOImpl(factory.createEntityManager());
        passengerDAO = new SinglePassengerDAOImpl(factory.createEntityManager());
        anotherSheduleDAO = new SingleAnotherSheduleDAOImpl(factory.createEntityManager());
    }

    public static void main(String[] args) {
        log.debug("Server started");
        try {
            new MyServerImpl().execute();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public void execute() throws IOException, SQLException {

        boolean listening = true;
        //    CommandImpl command = new CommandImpl();
        try {
            serverSocket = new ServerSocket(SERVER_PORT);
        } catch (IOException e) {
            log.error("Could not listen on port");
            System.exit(1);
        }
        log.debug("execute Started");
        while (listening) {
            new MultiServerThread(serverSocket.accept(), factory.createEntityManager()).start();
        }
        serverSocket.close();
    }


//    public void receive() {
//    }
//
//    public void reply() {
//    }
//    @Override
//    public void receiveRequest(RequestMessage requestMessage) {
//    }
//
//    @Override
//    public void replyResponse(ResponseMessage responseMessage) {
//    }

}
