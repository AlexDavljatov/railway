package com.tsystems.client.others;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Iterator;
import java.util.Set;

public class NonBlockingTcpClient {
    public static void main(String[] args) {
        final int DEFAULT_PORT = 9001;
        final String IP = "127.0.0.1";
        ByteBuffer receivingBuffer = ByteBuffer.allocateDirect(1024);
        ByteBuffer sendingBuffer = ByteBuffer.wrap("Hello".getBytes());
        Charset charset = Charset.defaultCharset();
        CharsetDecoder decoder = charset.newDecoder();
        String responseMsg = "";
        //open Selector and ServerSocketChannel
        //try (Selector selector = Selector.open();
        //     SocketChannel socketChannel = SocketChannel.open())
        try {
            Selector selector = Selector.open();
            SocketChannel socketChannel = SocketChannel.open();
            //check that both of them were opened
            if ((socketChannel.isOpen()) && (selector.isOpen())) {
                //configure non-blocking mode
                socketChannel.configureBlocking(false);
                //register the current channel with the given selector
                socketChannel.register(selector, SelectionKey.OP_CONNECT);
                socketChannel.connect(new java.net.InetSocketAddress(IP, DEFAULT_PORT));

                //waiting for the connection
                while (selector.select(1000) > 0) {
                    //get keys
                    Set keys = selector.selectedKeys();
                    Iterator its = keys.iterator();
                    //process each key
                    while (its.hasNext()) {
                        SelectionKey key = (SelectionKey) its.next();
                        //remove the current key
                        its.remove();
                        //get the socket channel for this key
                        //try (SocketChannel keySocketChannel = (SocketChannel) key.channel())
                        try {
                            SocketChannel keySocketChannel = (SocketChannel) key.channel();
                            //attempt a connection
                            if (key.isConnectable()) {
                                //make sure the connection estqablishment has been finished
                                if (keySocketChannel.isConnectionPending()) {
                                    keySocketChannel.finishConnect();
                                }
                                keySocketChannel.write(sendingBuffer);
                                keySocketChannel.shutdownOutput();
                                long startTime = System.currentTimeMillis();
                                while (keySocketChannel.read(receivingBuffer) != -1) {
                                    long elapsedTime = System.currentTimeMillis() - startTime;
                                    System.out.println("elapsedTime=" + elapsedTime);
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
                        } catch (IOException ex) {
                            System.err.println(ex);
                        }
                    }
                }
            } else {
                System.out.println("The socket channel or selector cannot be opened!");
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
