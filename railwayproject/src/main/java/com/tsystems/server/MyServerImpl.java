package com.tsystems.server;

import com.tsystems.server.domain.dao.users.impl.UserDAOImpl;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.net.ServerSocket;
import java.sql.SQLException;

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

    public static final EntityManagerFactory factory =
            Persistence.createEntityManagerFactory("myapp");

    private UserDAOImpl userDAO;

    public MyServerImpl() {
        userDAO = new UserDAOImpl(factory.createEntityManager());
    }

    public static void main(String[] args) {
        System.err.println("Server started");
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
            System.err.println("Could not listen on port");
            System.exit(1);
        }
        System.err.println("execute Started");
        while (listening) {
            new MultiServerThread(serverSocket.accept(), factory.createEntityManager()).start();
        }
        serverSocket.close();
    }


    //TODO: read a message from the client
    public void receive() {
    }

    //TODO: write message to the client
    public void reply() {
    }
/*
    @Override
    public void receiveRequest(RequestMessage requestMessage) {
    }

    @Override
    public void replyResponse(ResponseMessage responseMessage) {
    }
*/
}
