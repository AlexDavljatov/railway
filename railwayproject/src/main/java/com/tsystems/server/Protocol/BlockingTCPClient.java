package com.tsystems.server.Protocol;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 2/20/13
 * Time: 7:18 PM
 * To change this template use File | Settings | File Templates.
 */

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class BlockingTCPClient {
    public static void main(String[] args)
            throws IOException {
        final int SERVER_PORT = 9001;
        final String SERVER_IP = "127.0.0.1";
        ByteBuffer receivingBuffer = ByteBuffer.allocateDirect(2);
        ByteBuffer sendingBuffer = ByteBuffer.wrap("Hello".getBytes());
        Charset charset = Charset.defaultCharset();
        CharsetDecoder decoder = charset.newDecoder();
        String responseMsg = "";
        //create a new socket channel
        try {
            SocketChannel socketChannel = SocketChannel.open();
            if (socketChannel.isOpen()) {
                //set the blocking mode
                socketChannel.configureBlocking(true);

                socketChannel.setOption(StandardSocketOptions.SO_SNDBUF, 1024);
                socketChannel.setOption(StandardSocketOptions.SO_RCVBUF, 1024);
                socketChannel.setOption(StandardSocketOptions.SO_LINGER, 10);

                //establish channel connection
                socketChannel.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT));
                if (socketChannel.isConnected()) {
                    //sending data
                    socketChannel.write(sendingBuffer);
                    socketChannel.shutdownOutput();
                    //receving data
                    while (socketChannel.read(receivingBuffer) != -1) {
                        receivingBuffer.flip();
                        String msgReceived = decoder.decode(receivingBuffer).toString();
                        System.out.println("Msg received in this loop : " + msgReceived);
                        responseMsg = responseMsg + msgReceived;
                        if (receivingBuffer.hasRemaining()) {
                            receivingBuffer.compact();
                        } else {
                            receivingBuffer.clear();
                        }
                    }

                    System.out.println("Response from server : " + responseMsg);
                } else {
                    System.out.println("The connection cannot be established!");
                }
            } else {
                System.out.println("The socket channel cannot be opened!");
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }


    }
}