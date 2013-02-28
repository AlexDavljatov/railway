package com.tsystems.client;

import com.tsystems.common.DataTransferObject;
import com.tsystems.common.User;
import com.tsystems.server.Protocol.Command.CommandType;
import com.tsystems.server.Protocol.MyProtocol;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutionException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 2/19/13
 * Time: 10:37 PM
 * To change this template use File | Settings | File Templates.
 */
//public class MyClientImpl implements Runnable {
public class MyClientImpl {

    final int SERVER_PORT = 9090;
    final String SERVER_IP = "localhost";
    //static boolean completed = false;
    //AsynchronousSocketChannel client = null;
    Socket socket;
    //ByteBuffer message = null;
    ObjectInputStream in;
    ObjectOutputStream out;

    public MyClientImpl() throws UnknownHostException {
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
    //String s = null;

    public boolean doRegister() throws IOException, ClassNotFoundException {
        System.err.println("doRegister");
        out.writeObject(new DataTransferObject(CommandType.REGISTER, new User("Vasya", "Sidorov", "bbb@bbb.bbb", "password")));
        DataTransferObject input = (DataTransferObject) in.readObject();
        System.err.println(input.getCmd());
        /**     System.err.println("Try to register: " + user);
         out.writeObject(new DataTransferObject(MyProtocol.Command.REGISTER, user));
         DataTransferObject input = (DataTransferObject) in.readObject();
         System.err.println(input.getCmd());
         processCommand(input.getCmd());
         */
        out.close();
        in.close();
        return input.getCmd() == CommandType.OK;

        //    return false;
    }

/*    public static void main(String[] args) throws Exception {
        new MyClientImpl();
    }
*/
    /**
     * MyClientImpl(String s) {
     * this.s = s;
     * }
     * /
     * <p/>
     * /**
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
/***
 public static void main(String[] args) {
 ///    for (int i = 0; i < 5; i++)
 new Thread(new MyClientImpl(String.valueOf(i))).start();
 }

 //TODO: connect to the Server
 void connectToServer() throws IOException {
 //    client = AsynchronousSocketChannel.open();
 //client.connect(server.getLocalAddress()).get();
 }

 //TODO: send a message to the Server
 void sendMessageToServer() throws ExecutionException, InterruptedException {
 ByteBuffer message = ByteBuffer.wrap("ping".getBytes());
 client.write(message).get();
 }

 //TODO: get message from the server


 @Override public void run() {
 //To change body of implemented methods use File | Settings | File Templates.
 }
 */
    /**
     *
     @Override public void run() {
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
     */
}
