package com.tsystems.server;

import com.tsystems.server.Protocol.Command.Command;
import com.tsystems.server.Protocol.Command.CommandImpl;

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
            new MultiServerThread(serverSocket.accept()).start();
        }
        serverSocket.close();
    }
//    AsynchronousServerSocketChannel server = null;
/**
 MyServerImpl() {
 try {
 server = AsynchronousServerSocketChannel.open();
 } catch (IOException e) {
 System.out.println("Server opening error\n");
 e.printStackTrace();
 } catch (Exception e) {
 System.out.println("Unhandled exception is server opening block\n");
 e.printStackTrace();
 }
 }

 public static void main(String[] args) {
 new Thread(new MyServerImpl()).start();
 }

 public void run() {
 try {
 AsynchronousServerSocketChannel asynchronousServerSocketChannel = AsynchronousServerSocketChannel.open();
 if (asynchronousServerSocketChannel.isOpen()) {
 asynchronousServerSocketChannel.bind(new InetSocketAddress(SERVER_IP, SERVER_PORT));

 System.out.println("Waiting for connections ...");
 while (true) {
 Future<AsynchronousSocketChannel> asynchronousSocketChannelFuture =
 asynchronousServerSocketChannel.accept();
 try {
 AsynchronousSocketChannel asynchronousSocketChannel = asynchronousSocketChannelFuture.get();
 System.out.println("Incoming connection from: " + asynchronousSocketChannel.getRemoteAddress());
 ByteBuffer incomingBuffer = ByteBuffer.allocateDirect(1024);

 //receive
 asynchronousSocketChannel.read(incomingBuffer, incomingBuffer, new CompletionHandler<Integer, ByteBuffer>() {
 public void completed(Integer result, ByteBuffer buffer) {
 buffer.flip();
 String msgReceived = Charset.defaultCharset().decode(buffer).toString();
 System.out.println("Msg received from the client : " + msgReceived);
 }

 public void failed(Throwable exc, ByteBuffer buffer) {
 throw new UnsupportedOperationException("read failed!");
 }
 });
 /*
 try {
 Thread.sleep(5000);
 } catch (Exception e) {
 }
 */
    //reply
      /*                  ByteBuffer outgoingBuffer = ByteBuffer.wrap("World".getBytes());
                        asynchronousSocketChannel.write(outgoingBuffer).get();
                    } catch (Exception e) {
                        System.err.println(e);
                    }
                }
            } else {
                System.out.println("The asynchronous server-socket channel cannot be opened!");
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
     /*
        try {
            server = AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(SERVER_IP, SERVER_PORT));

            //First possibility to use ASSC - using java.concurrent.Future object
            //Second - java.nio.channels.CompletionHandler
            //Future<AsynchronousSocketChannel> acceptFuture = server.accept();

            //Block and wait for the result
            //AsynchronousSocketChannel worker = future.get();/future.get(10, TimeUnit.SECONDS);

            //worker.read(readBuffer).get(10, TimeUnit.SECONDS);
            //System.out.println("Message: " + new String(readBuffer.array()));

        } catch (IOException e) {
            System.out.println("Server binding problem\n");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Unhandled exception in server binding block\n");
            e.printStackTrace();
        }
        */
/*    }

    //TODO: read a message from the client
    public void receive() {
    }

    //TODO: write message to the client
    public void reply() {
    }

    @Override
    public void receiveRequest(RequestMessage requestMessage) {
    }

    @Override
    public void replyResponse(ResponseMessage responseMessage) {
    }
        */
}
