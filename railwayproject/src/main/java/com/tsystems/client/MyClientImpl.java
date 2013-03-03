package com.tsystems.client;

import com.tsystems.common.*;
import com.tsystems.common.model.CommonModel;
import com.tsystems.common.model.LoginPassword;
import com.tsystems.common.model.User;
import com.tsystems.common.command.CommandType;
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

    public List<CommonModel> getTrains(LoginPassword lp) throws IOException, ClassNotFoundException {
        init();
        out.writeObject(new DataTransferObject(CommandType.ADMIN_ADD_TRAIN, lp));
        DataTransferObject input = (DataTransferObject) in.readObject();
        out.close();
        in.close();
        log.debug("MyClient.getTrains() success ");
        return (List<CommonModel>) input.getData();
    }

    public List<CommonModel> getStation(LoginPassword lp) throws IOException, ClassNotFoundException {
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
}
