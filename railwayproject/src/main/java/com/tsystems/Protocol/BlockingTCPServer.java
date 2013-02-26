package com.tsystems.Protocol;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 2/20/13
 * Time: 7:17 PM
 * To change this template use File | Settings | File Templates.
 */

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class BlockingTCPServer {
    public static void main(String[] args) {
        final int SERVER_PORT = 9001;
        final String SERVER_IP = "127.0.0.1";

        ByteBuffer incomingBuffer = ByteBuffer.allocateDirect(2);
        ByteBuffer outgoingBuffer = ByteBuffer.wrap("World".getBytes());
        Charset charset = Charset.defaultCharset();
        CharsetDecoder decoder = charset.newDecoder();
        String requestMsg = "";

        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            if (serverSocketChannel.isOpen()) {
                serverSocketChannel.configureBlocking(true);

                //set options
                serverSocketChannel.setOption(StandardSocketOptions.SO_RCVBUF, 1024);
                serverSocketChannel.setOption(StandardSocketOptions.SO_REUSEADDR, true);

                //bind the server socket channel to local address
                serverSocketChannel.bind(new InetSocketAddress(SERVER_IP, SERVER_PORT));
                while (true) {
                    try {
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        while (socketChannel.read(incomingBuffer) != -1) {
                            incomingBuffer.flip();
                            String msgReceived = decoder.decode(incomingBuffer).toString();
                            System.out.println("Msg received in this loop : " + msgReceived);
                            requestMsg = requestMsg + msgReceived;
                            if (incomingBuffer.hasRemaining()) {
                                incomingBuffer.compact();
                            } else {
                                incomingBuffer.clear();
                            }
                        }
                        System.out.println("Request from " + socketChannel.getRemoteAddress()
                                + " : " + requestMsg);
                        socketChannel.write(outgoingBuffer);
                        outgoingBuffer.flip();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            } else {
                System.out.println("The server socket channel cannot be opened!");
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}