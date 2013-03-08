package com.tsystems.server;

import com.tsystems.common.DataTransferObject;
import com.tsystems.server.protocol.MyProtocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 2/28/13
 * Time: 2:39 AM
 * To change this template use File | Settings | File Templates.
 */

public class MultiServerThread extends Thread {
    private static final Logger log = LoggerFactory.getLogger(MultiServerThread.class);


    private Socket socket = null;
    private EntityManager em;

    public MultiServerThread(Socket socket, EntityManager em) {
        super("MultiServerThread");
        this.socket = socket;
        this.em = em;
    }

    public void run() {
        log.debug("got connection");
        try {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            MyProtocol protocol = new MyProtocol(em);
            boolean working = true;
            while (working) {
                DataTransferObject input = (DataTransferObject) in.readObject();
                DataTransferObject output = protocol.processInput(input);
                out.writeObject(output);
                working = false;
            }
            out.close();
            in.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
