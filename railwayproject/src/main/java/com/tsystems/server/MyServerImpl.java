package com.tsystems.server;

import com.tsystems.server.domain.dao.stations.SingleStationDAO;
import com.tsystems.server.domain.dao.stations.impl.SingleStationDAOImpl;
import com.tsystems.server.domain.dao.trains.SingleTrainDAO;
import com.tsystems.server.domain.dao.trains.impl.SingleTrainDAOImpl;
import com.tsystems.server.domain.dao.users.UserDAO;
import com.tsystems.server.domain.dao.users.impl.UserDAOImpl;
import com.tsystems.server.domain.entity.Passenger;
import com.tsystems.server.domain.entity.Station;
import com.tsystems.server.domain.entity.Train;
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

    //TODO: remove init method()
    private void init() {
        EntityManager em = factory.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(new Train(50, 600, null, null));
        em.persist(new Train(150, 800, null, null));
        em.persist(new Passenger("aaaa0", "aaaa0", "aaaa0", "aaaa0", new Date(Date.parse("Sat, 12 Aug 1995 13:30:00 GMT")), false));
        em.persist(new Passenger("s", "s", "s", "s", new Date(Date.parse("Sat, 12 Aug 1995 13:30:00 GMT")), true));
        em.persist(new Passenger("q", "q", "q", "q", new Date(Date.parse("Sat, 12 Aug 1995 13:30:00 GMT")), true));
        em.persist(new Train(150, 800, null, null));
        em.persist(new Station("Saint-Petersburg", null));
        em.persist(new Station("Berlin", null));
        em.persist(new Station("Frankfurt", null));

        et.commit();
        em.close();
    }

    public MyServerImpl() {
        init();
        userDAO = new UserDAOImpl(factory.createEntityManager());
        trainDAO = new SingleTrainDAOImpl(factory.createEntityManager());
        stationDAO = new SingleStationDAOImpl(factory.createEntityManager());
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
