package com.tsystems.server.Protocol;

import java.io.IOException;
import java.net.Socket;

/**
 * @author Ruslan Sverchkov
 */
public class Client {
    static int index;

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 9090);
        socket.getOutputStream().write(("CLIENT: Hi, I'm a client " + (index) + " !").getBytes());
        byte[] data = new byte[100];
        socket.getInputStream().read(data);
        System.out.print(new String(data));
        System.out.println(("CLIENT: out " + (index++) + " !").getBytes());
        socket.close();
    }

}