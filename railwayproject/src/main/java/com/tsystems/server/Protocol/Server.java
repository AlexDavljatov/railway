package com.tsystems.server.Protocol;

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
        System.out.println("    main in!");
        Worker worker = new Worker();
        new Thread(worker).start();
        System.out.println("    Next thread");
        new Thread(new Server(worker)).start();
        System.out.println("    main out!");
    }

    public Server(Worker worker) throws IOException {
        System.out.println("    Constructor in!");
        this.worker = worker;
        this.selector = Selector.open();
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.socket().bind(new InetSocketAddress(9090));
        channel.configureBlocking(false);
        channel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("    Constructor out!");
    }

    public synchronized void send(SocketChannel channel, byte[] data) {
        System.out.println("    send method in");
        changeRequests.add(new ChangeRequest(channel, SelectionKey.OP_WRITE));
        Queue queue = pendingData.get(channel);
        if (queue == null) {
            queue = new ArrayDeque();
            pendingData.put(channel, queue);
        }
        queue.add(ByteBuffer.wrap(data));
        selector.wakeup();
        System.out.println("    send method out");
    }

    @Override
    public void run() {
        System.out.println("    run method in");
        int i = 0;
        while (true) {
            System.out.println("    " + i + " iteration in");
            try {
                processChangeRequests();
                System.out.println("Waiting for events");
                selector.select();
                System.out.println("Got an event! Yuppi!");
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
            } finally {
                System.out.println("    " + (i++) + " interation out");
            }
        }
//        System.out.println("run method out");
    }

    private synchronized void processChangeRequests() {
        System.out.println("    processChangeRequests in");
        int index = 0;
        for (ChangeRequest request : changeRequests) {
            System.out.println("    processChangeRequestLoop: " + (index++));
            SelectionKey key = request.getChannel().keyFor(selector);
            key.interestOps(request.getOperation());  //типа взяли ссылку и сказали, что теперь нас интересует OP_WRITE?
        }
        System.out.println("    processChangeRequests out");
        changeRequests.clear();
    }

    private void accept(SelectionKey key) throws IOException {
        System.out.println("    accept in");
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
        SocketChannel socketChannel = serverSocketChannel.accept();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
        System.out.println("    accept out");
    }

    private void read(SelectionKey key) throws IOException {
        System.out.println("    read in");
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
        System.out.println("    read out");
    }

    private synchronized void write(SelectionKey key) throws IOException {
        System.out.println("    write in");
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
        System.out.println("    write out");
    }

}