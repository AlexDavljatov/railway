package com.tsystems.Protocol;

import java.io.IOException;
import java.net.Socket;

/**
 * @author Ruslan Sverchkov
 */
public class Client {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 9090);
        socket.getOutputStream().write("Hi, I'm a client2!".getBytes());
        byte[] data = new byte[100];
        socket.getInputStream().read(data);
        System.out.print(new String(data));
        socket.close();
    }

}