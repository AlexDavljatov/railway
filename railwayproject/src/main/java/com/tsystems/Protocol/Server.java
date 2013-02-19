package com.tsystems.Protocol;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.*;

/**
 * @author Ruslan Sverchkov
 */
public class Server implements Runnable {

    private final Queue<ChangeRequest> changeRequests = new ArrayDeque<ChangeRequest>();
    private final Map<SocketChannel, Queue<ByteBuffer>> pendingData = new HashMap<SocketChannel, Queue<ByteBuffer>>();
    private final Selector selector;
    private final ByteBuffer buffer = ByteBuffer.allocate(100);
    private final Worker worker;

    public static void main(String[] args) throws IOException {
        Worker worker = new Worker();
        new Thread(worker).start();
        new Thread(new Server(worker)).start();
    }

    public Server(Worker worker) throws IOException {
        this.worker = worker;
        this.selector = Selector.open();
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.socket().bind(new InetSocketAddress(9090));
        channel.configureBlocking(false);
        channel.register(selector, SelectionKey.OP_ACCEPT);
    }

    public synchronized void send(SocketChannel channel, byte[] data) {
        changeRequests.add(new ChangeRequest(channel, SelectionKey.OP_WRITE));
        Queue queue = pendingData.get(channel);
        if (queue == null) {
            queue = new ArrayDeque();
            pendingData.put(channel, queue);
        }
        queue.add(ByteBuffer.wrap(data));
        selector.wakeup();
    }

    @Override
    public void run() {
        while (true) {
            try {
                processChangeRequests();
                System.out.println("Waiting for events");
                selector.select();
                System.out.println("Got an event");
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = keys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    if (key.isValid() && key.isAcceptable()) {
                        System.out.println("Accepting");
                        accept(key);
                    }
                    if (key.isValid() && key.isReadable()) {
                        System.out.println("Reading");
                        read(key);
                    }
                    if (key.isValid() && key.isWritable()) {
                        System.out.println("Writing");
                        write(key);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private synchronized void processChangeRequests() {
        for (ChangeRequest request : changeRequests) {
            SelectionKey key = request.getChannel().keyFor(selector);
            key.interestOps(request.getOperation());
        }
        changeRequests.clear();
    }

    private void accept(SelectionKey key) throws IOException {
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
        SocketChannel socketChannel = serverSocketChannel.accept();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
    }

    private void read(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        buffer.clear();
        int read;
        try {
            read = socketChannel.read(buffer);
        } catch (IOException e) {
            read = -1;
        }
        if (read == -1) {
            System.out.println("Closing the channel");
            key.cancel();
            socketChannel.close();
            return;
        }
        worker.process(this, socketChannel, buffer.array(), read);
    }

    private synchronized void write(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        Queue<ByteBuffer> queue = pendingData.get(channel);
        while (!queue.isEmpty()) {
            ByteBuffer buffer = queue.element();
            channel.write(buffer);
            if (buffer.hasRemaining()) {
                break;
            }
            queue.remove();
        }
        if (queue.isEmpty()) {
            key.interestOps(SelectionKey.OP_READ);
        }
    }

}