package com.tsystems.server;

import com.tsystems.common.DataTransferObject;
import com.tsystems.server.Protocol.MyProtocol;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 2/28/13
 * Time: 2:39 AM
 * To change this template use File | Settings | File Templates.
 */

public class MultiServerThread extends Thread {
    private Socket socket = null;

    public MultiServerThread(Socket socket) {
        super("MultiServerThread");
        this.socket = socket;
    }

    public void run() {
        System.err.println("got connection");
        try {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            MyProtocol protocol = new MyProtocol();
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
