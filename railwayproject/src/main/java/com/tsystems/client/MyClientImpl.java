package com.tsystems.client;

import com.tsystems.common.DataTransferObject;
import com.tsystems.common.command.CommandType;
import com.tsystems.common.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 2/19/13
 * Time: 10:37 PM
 * To change this template use File | Settings | File Templates.
 */
//public class MyClientImpl implements Runnable {
public class MyClientImpl {

    private static final Logger log = LoggerFactory.getLogger(MyClientImpl.class);

    final int SERVER_PORT = 9090;
    final String SERVER_IP = "localhost";
    //static boolean completed = false;
    //AsynchronousSocketChannel client = null;
    Socket socket;
    //ByteBuffer message = null;
    ObjectInputStream in;
    ObjectOutputStream out;

    private LoginPassword lp = null;

    private static MyClientImpl instance;

    public static synchronized MyClientImpl getInstance() {
        if (instance == null) instance = new MyClientImpl();
        return instance;
    }

    public MyClientImpl() {

    }
/*    public MyClientImpl() throws UnknownHostException {
        System.err.println("MyClient started");
        InetAddress address = InetAddress.getByName(SERVER_IP);
        try {
            socket = new Socket(address, SERVER_PORT);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (java.rmi.UnknownHostException e) {
            System.err.println("Don't know about host");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection");
            System.exit(1);
        }
    }
 */   //String s = null;

    private void init() throws UnknownHostException {
        InetAddress address = InetAddress.getByName(SERVER_IP);
        try {
            socket = new Socket(address, SERVER_PORT);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            log.debug("MyClient.init() success ");
        } catch (java.rmi.UnknownHostException e) {
            log.error("MyClient.init() host exception " + e.getMessage());
            System.exit(1);
        } catch (IOException e) {
            log.error("MyClient.init() I/O exception " + e.getMessage());
            System.exit(1);
        }
    }

    public boolean doRegister(User user) throws IOException, ClassNotFoundException {
        init();
        this.lp = new LoginPassword(user.getEmail(), user.getPassword());
        out.writeObject(new DataTransferObject(CommandType.REGISTER, user));
        DataTransferObject input = (DataTransferObject) in.readObject();
        //System.err.println(input.getCmd());
        /**     System.err.println("Try to register: " + user);
         out.writeObject(new DataTransferObject(MyProtocol.Command.REGISTER, user));
         DataTransferObject input = (DataTransferObject) in.readObject();
         System.err.println(input.getCmd());
         processCommand(input.getCmd());
         */
        out.close();
        in.close();
        log.debug("\nMyClient.doRegister() success "); //+ getLp().getLogin());
        log.debug("\nMyClient.doRegister() success " + MyClientImpl.getInstance().getLp().getLogin());
        log.debug("\nMyClient.doRegister() Received CommandType " + input.getCmd());
        return input.getCmd() == CommandType.OK;

    }

    public boolean doLogin(LoginPassword lp) throws IOException, ClassNotFoundException {
        init();
        this.lp = lp;
        out.writeObject(new DataTransferObject(CommandType.LOGIN, lp));
        DataTransferObject input = (DataTransferObject) in.readObject();
        out.close();
        in.close();
        //    return ()input.getData();
        log.debug("\nReceived CommandType " + input.getCmd());
        log.debug("\nMyClient.doLogin() " + getLp().getLogin());
        return input.getCmd() == CommandType.OK;
    }

    public List<CommonModel> getUsers(LoginPassword lp) throws IOException, ClassNotFoundException {
        init();
        out.writeObject(new DataTransferObject(CommandType.ADMIN_USERS_WATCH_EDIT, lp));
        DataTransferObject input = (DataTransferObject) in.readObject();
        out.close();
        in.close();
        log.debug("MyClient.getUsers() success ");
        log.debug("MyClient.getUsers() data " + input.getData());
        log.debug("MyClient.getUsers() data " + (List<CommonModel>) input.getData());
        return (List<CommonModel>) input.getData();
    }

    public List<CommonModel> getTrains() throws IOException, ClassNotFoundException {
        init();
        out.writeObject(new DataTransferObject(CommandType.ADMIN_ADD_TRAIN, lp));
        DataTransferObject input = (DataTransferObject) in.readObject();
        out.close();
        in.close();
        log.debug("MyClient.getTrains() success ");
        return (List<CommonModel>) input.getData();
    }

    public List<CommonModel> getStation() throws IOException, ClassNotFoundException {
        init();
        out.writeObject(new DataTransferObject(CommandType.ADMIN_ADD_STATION, lp));
        DataTransferObject input = (DataTransferObject) in.readObject();
        out.close();
        in.close();
        log.debug("MyClient.getTrains() success ");
        return (List<CommonModel>) input.getData();
    }

    //TODO: handle with data verification
    public LoginPassword getLp() {
        return lp;
    }

    public boolean isAdmin() throws IOException, ClassNotFoundException {
        init();
        out.writeObject(new DataTransferObject(CommandType.IS_ADMIN, getLp()));
        DataTransferObject input = (DataTransferObject) in.readObject();
        out.close();
        in.close();
        log.debug("MyClient.isAdmin() success" + lp.getLogin() + " " + input.getCmd());
        return (input.getCmd() == CommandType.OK);
    }

    public List<CommonModel> getSheduleByStation(String station) throws IOException, ClassNotFoundException {
        init();
        out.writeObject(new DataTransferObject(CommandType.GET_SHEDULE_BY_STATION, station));
//        out.writeObject(new DataTransferObject(CommandType.GET_SHEDULE_BY_STATION_TEST, station));
        DataTransferObject input = (DataTransferObject) in.readObject();
        out.close();
        in.close();
        log.debug("MyClient.getSheduleByStation()" + input.getCmd() + " " + lp.getLogin() + " " + input.getData());
        return (List<CommonModel>) input.getData();
    }

    public List<CommonModel> getTickets() throws IOException, ClassNotFoundException {
        init();
        out.writeObject(new DataTransferObject(CommandType.VIEW_TICKETS, lp));
        DataTransferObject input = (DataTransferObject) in.readObject();
        out.close();
        in.close();
        log.debug("MyClient.getTickets()" + input.getCmd() + " " + lp.getLogin() + " " + input.getData());
        for (CommonModel ticket : (List<CommonModel>) input.getData()) {
            log.debug("" + ((Ticket) ticket).getTrain().getNumber());
        }
        return (List<CommonModel>) input.getData();

    }

    public boolean addTrainToDB(Train train) throws IOException, ClassNotFoundException {
        init();
        out.writeObject(new DataTransferObject(CommandType.ADMIN_ADD_TRAIN_DB, lp, train));
        DataTransferObject input = (DataTransferObject) in.readObject();
        out.close();
        in.close();
        log.debug("MyClient.getTickets()" + input.getCmd() + " " + lp.getLogin() + " " + input.getData());
        return input.getCmd() == CommandType.OK;
    }

    public boolean addStationDB(Station station) throws IOException, ClassNotFoundException {
        init();
        out.writeObject(new DataTransferObject(CommandType.ADMIN_ADD_STATION_DB, lp, station));
        DataTransferObject input = (DataTransferObject) in.readObject();
        out.close();
        in.close();
        log.debug("MyClient.getTickets() " + input.getCmd() + " " + lp.getLogin() + " " + input.getData());
        return input.getCmd() == CommandType.OK;

    }

    public boolean addPassengerDB(User user) throws IOException, ClassNotFoundException {
        init();
        out.writeObject(new DataTransferObject(CommandType.ADMIN_USERS_WATCH_EDIT_DB, lp, user));
        DataTransferObject input = (DataTransferObject) in.readObject();
        out.close();
        in.close();
        log.debug("MyClient.getTickets() " + input.getCmd() + " " + lp.getLogin() + " " + input.getData());
        return input.getCmd() == CommandType.OK;

    }

    public List<CommonModel> getPassengersByTrainNumber(String value) throws IOException, ClassNotFoundException {
        init();
        out.writeObject(new DataTransferObject(CommandType.ADMIN_GET_PASSENGERS_BY_TRAIN_NUMBER, value));
        //        out.writeObject(new DataTransferObject(CommandType.GET_SHEDULE_BY_STATION_TEST, station));
        DataTransferObject input = (DataTransferObject) in.readObject();
        out.close();
        in.close();
        log.debug("MyClient. getPassengersByTrainNumber() " + input.getCmd() + " " + lp.getLogin() + " " + input.getData());
        return (List<CommonModel>) input.getData();
    }

    public List<CommonModel> getAnotherSheduleByStation(String value) throws IOException, ClassNotFoundException {
        init();
        out.writeObject(new DataTransferObject(CommandType.GET_ANOTHER_SHEDULE_BY_STATION, value));
        //        out.writeObject(new DataTransferObject(CommandType.GET_SHEDULE_BY_STATION_TEST, station));
        DataTransferObject input = (DataTransferObject) in.readObject();
        out.close();
        in.close();
        log.debug("MyClient.getAnotherSheduleByStation() " + input.getCmd() + " " + lp.getLogin() + " " + input.getData());
        return (List<CommonModel>) input.getData();
    }

    public boolean buyTicket(String train, String station) throws IOException, ClassNotFoundException {
        init();
        log.debug("MyClient.buyTicket() " + train + " " + station + " " + lp.getLogin());
        out.writeObject(new DataTransferObject(CommandType.BUY_TICKET, lp, train, station));
        //        out.writeObject(new DataTransferObject(CommandType.GET_SHEDULE_BY_STATION_TEST, station));
        DataTransferObject input = (DataTransferObject) in.readObject();
        out.close();
        in.close();
        log.debug("MyClient.buyTicket() " + input.getCmd() + " " + lp.getLogin() + " " + input.getData() + " " + input.getCmd());
        return input.getCmd() == CommandType.OK;

    }


    public List<CommonModel> findTrains(String st1, String st2, long date1, long date2) throws IOException, ClassNotFoundException {
        init();
        log.debug("MyClient.findTrains() " + date1 + " " + date2 + " " + lp.getLogin());
        out.writeObject(new DataTransferObject(CommandType.FIND_TRAIN, lp, st1, st2, date1, date2));
        //        out.writeObject(new DataTransferObject(CommandType.GET_SHEDULE_BY_STATION_TEST, station));
        DataTransferObject input = (DataTransferObject) in.readObject();
        out.close();
        in.close();
        log.debug("MyClient.buyTicket() " + input.getCmd() + " " + lp.getLogin() + " " + input.getData() + " " + input.getCmd());
        return (List<CommonModel>) input.getData();
//        return null;
    }

    public boolean addRoutePoint(String station, String train, long time) throws IOException, ClassNotFoundException {
        init();
        log.debug("MyClient.addPoint() " + station + " " + train + " " + lp.getLogin());
        out.writeObject(new DataTransferObject(CommandType.ADD_ROUTE_POINT, lp, station, train, time));
        //        out.writeObject(new DataTransferObject(CommandType.GET_SHEDULE_BY_STATION_TEST, station));
        DataTransferObject input = (DataTransferObject) in.readObject();
        out.close();
        in.close();
        log.debug("MyClient.addPoint() " + input.getCmd() + " " + lp.getLogin() + " " + input.getData() + " " + input.getCmd());
        return input.getCmd() == CommandType.OK;
    }
}
