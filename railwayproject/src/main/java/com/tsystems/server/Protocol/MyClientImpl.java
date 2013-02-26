package com.tsystems.server.Protocol;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutionException;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 2/19/13
 * Time: 10:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class MyClientImpl implements Runnable {

    final int SERVER_PORT = 9090;
    final String SERVER_IP = "localhost";
    static boolean completed = false;
    AsynchronousSocketChannel client = null;
    ByteBuffer message = null;
    String s = null;

    MyClientImpl(String s) {
        this.s = s;
    }

    /**
     * MyClientImpl(AsynchronousServerSocketChannel server) {
     * try {
     * client = AsynchronousSocketChannel.open();
     * client.connect(server.getLocalAddress()).get();
     * } catch (IOException e) {
     * System.out.println("Connection exception");
     * e.printStackTrace();
     * } catch (InterruptedException e) {
     * System.out.println("You were interrupted.. Oooops!");
     * e.printStackTrace();
     * } catch (ExecutionException e) {
     * System.out.println("Error while thread executing");
     * e.printStackTrace();
     * }
     * }
     */

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++)
            new Thread(new MyClientImpl(String.valueOf(i))).start();
    }

    //TODO: connect to a Server
    void connectToServer() throws IOException {
        client = AsynchronousSocketChannel.open();
        //client.connect(server.getLocalAddress()).get();
    }

    //TODO: send a message to a Server
    void sendMessageToServer() throws ExecutionException, InterruptedException {
        ByteBuffer message = ByteBuffer.wrap("ping".getBytes());
        client.write(message).get();
    }
    //TODO: get message from server

    @Override
    public void run() {
        ByteBuffer receivingBuffer = ByteBuffer.allocateDirect(1024);
        ByteBuffer sendingBuffer = ByteBuffer.wrap(s.getBytes());//s = "Hi"

        try {
            AsynchronousSocketChannel asynchronousSocketChannel = AsynchronousSocketChannel.open();
            if (asynchronousSocketChannel.isOpen()) {

                Void connect = asynchronousSocketChannel.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT)).get();
                if (connect == null) {
                    System.out.println(s + " Local address: " + asynchronousSocketChannel.getLocalAddress());

                    //sending data
                    asynchronousSocketChannel.write(sendingBuffer).get();
                    asynchronousSocketChannel.read(receivingBuffer, receivingBuffer, new CompletionHandler<Integer, ByteBuffer>() {
                        public void completed(Integer result, ByteBuffer buffer) {
                            buffer.flip();
                            String msgReceived = Charset.defaultCharset().decode(buffer).toString();
                            System.out.println(s + " Msg received from server : " + msgReceived);
                            completed = true;
                        }

                        public void failed(Throwable exc, ByteBuffer buffer) {
                            completed = false;
                            throw new UnsupportedOperationException("read failed!");
                        }
                    });

                    while (!completed) {
                        try {
                            Thread.sleep(100);
                        } catch (Exception e) {
                        }
                        System.out.println("Waiting for response from the server");
                    }

                } else {
                    System.out.println("The connection cannot be established!");
                }
            } else {
                System.out.println("The asynchronous socket channel cannot be opened!");
            }
        } catch (Exception ex) {
            System.err.println(ex);
        }


    }
}
