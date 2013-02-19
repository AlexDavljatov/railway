package com.tsystems.Protocol;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.concurrent.ExecutionException;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 2/19/13
 * Time: 10:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class MyClient implements Runnable{
    AsynchronousSocketChannel client;

    MyClient() {};
    MyClient(AsynchronousServerSocketChannel server) {
        try {
            client = AsynchronousSocketChannel.open();
            client.connect(server.getLocalAddress()).get();
        } catch (IOException e) {
            System.out.println("Connection exception");
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("You were interrupted.. Oooops!");
            e.printStackTrace();
        } catch (ExecutionException e) {
            System.out.println("Error while thread executing");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }

    void connectToServer() throws IOException {
        client = AsynchronousSocketChannel.open();
        //client.connect(server.getLocalAddress()).get();

    }

    void sendMessageToServer() throws ExecutionException, InterruptedException {
        ByteBuffer message = ByteBuffer.wrap("ping".getBytes());
        client.write(message).get();
    }

    @Override
    public void run() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
