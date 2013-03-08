package com.tsystems.server.others;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.util.concurrent.Future;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 2/28/13
 * Time: 4:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class MyAsynchServer {

    final int SERVER_PORT = 9090;
    final String SERVER_IP = "localhost";
    AsynchronousServerSocketChannel server;

    public void execute() {
        try {
            server = AsynchronousServerSocketChannel.open();
            if (server.isOpen()) {
                //bind to local address
                server.bind(new InetSocketAddress(SERVER_IP, SERVER_PORT));

                //display a waiting message
                System.out.println("Waiting for connections ...");
                while (true) {
                    Future<AsynchronousSocketChannel> channelFuture =
                            server.accept();
                    try {
                        AsynchronousSocketChannel channel = channelFuture.get();
                        System.out.println("Incoming connection from: " + channel.getRemoteAddress());
                        ByteBuffer incomingBuffer = ByteBuffer.allocateDirect(1024);
                        //receiving data
                        channel.read(incomingBuffer, incomingBuffer, new CompletionHandler<Integer, ByteBuffer>() {
                            public void completed(Integer result, ByteBuffer buffer) {
                                buffer.flip();
                                //System.out.println(buffer.array());

                                //DataTransferObject data = (DataTransferObject)(new Serializer().deserialize(buffer.array()));
                                //UserSer userSer = (UserSer) new Serializer().deserialize(buffer.array());
                                //System.out.println("Msg received : " + (String)(new Serializer().deserialize(buffer.array())));
                                String msgReceived = Charset.defaultCharset().decode(buffer).toString();
                               System.out.println("Msg received from the client : " + msgReceived);

                                //String msgReceived = Charset.defaultCharset().decode(buffer).toString();
                            }

                            public void failed(Throwable exc, ByteBuffer buffer) {
                                throw new UnsupportedOperationException("read failed!");
                            }
                        });
                       /* try {
                            Thread.sleep(5000);
                        } catch (Exception e) {
                        }
                       */ //replying data
                        ByteBuffer outgoingBuffer = ByteBuffer.wrap("OK".getBytes());
                        channel.write(outgoingBuffer).get();
                    }
                    //catch (IOException | InterruptedException | ExecutionException ex)
                    catch (Exception e) {
                        System.err.println(e);
                    }
                }
            } else {
                System.out.println("The asynchronous server-socket channel cannot be opened!");
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }

    }

    public static void main(String[] args) {
        new MyAsynchServer().execute();
    }
}
