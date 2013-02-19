package com.tsystems.Protocol;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.*;
import java.util.concurrent.Future;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 2/19/13
 * Time: 10:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class MyServer {
    final int SERVER_PORT = 9090;
    final String SERVER_IP = "localhost";

    public static void main(String[] args) {

    }

    void runServer() {
        try {
            AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(SERVER_IP, SERVER_PORT));
            Future<AsynchronousSocketChannel> acceptFuture = server.accept();
            //Block and wait for the result
            //AsynchronousSocketChannel worker = future.get();/future.get(10, TimeUnit.SECONDS);

            //TODO: read a message from the client
            //worker.read(readBuffer).get(10, TimeUnit.SECONDS);
            //System.out.println("Message: " + new String(readBuffer.array()));

        } catch (IOException e) {
            System.out.println("Connection problem" + "\n");
            e.printStackTrace();
        }
    }
}
