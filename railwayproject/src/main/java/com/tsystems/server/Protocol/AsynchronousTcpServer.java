package com.tsystems.server.Protocol;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.util.concurrent.Future;

public class AsynchronousTcpServer {
    public static void main(String[] args) {

        final int SERVER_PORT = 9090;
        final String SERVER_IP = "localhost";

        //create asynchronous server-socket channel bound to the default group
        //try (AsynchronousServerSocketChannel asynchronousServerSocketChannel = AsynchronousServerSocketChannel.open())
        try {
            AsynchronousServerSocketChannel asynchronousServerSocketChannel = AsynchronousServerSocketChannel.open();
            if (asynchronousServerSocketChannel.isOpen()) {
                //bind to local address
                asynchronousServerSocketChannel.bind(new InetSocketAddress(SERVER_IP, SERVER_PORT));

                //display a waiting message 
                System.out.println("Waiting for connections ...");
                while (true) {
                    Future<AsynchronousSocketChannel> asynchronousSocketChannelFuture =
                            asynchronousServerSocketChannel.accept();
                    //try (AsynchronousSocketChannel asynchronousSocketChannel = asynchronousSocketChannelFuture.get())
                    try {
                        AsynchronousSocketChannel asynchronousSocketChannel = asynchronousSocketChannelFuture.get();
                        System.out.println("Incoming connection from: " + asynchronousSocketChannel.getRemoteAddress());
                        ByteBuffer incomingBuffer = ByteBuffer.allocateDirect(1024);
                        //receiving data
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
                        try {
                            Thread.sleep(5000);
                        } catch (Exception e) {
                        }

                        //replying data
                        ByteBuffer outgoingBuffer = ByteBuffer.wrap("World".getBytes());
                        asynchronousSocketChannel.write(outgoingBuffer).get();
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
}
