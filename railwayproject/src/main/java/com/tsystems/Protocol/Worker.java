package com.tsystems.Protocol;

import java.nio.channels.SocketChannel;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author Ruslan Sverchkov
 */
public class Worker implements Runnable {

    private final BlockingQueue<ServerEvent> queue = new ArrayBlockingQueue<ServerEvent>(100);

    public void process(Server server, SocketChannel channel, byte[] data, int read) {
        byte[] copy = new byte[data.length];
        System.arraycopy(data, 0, copy, 0, read);
        queue.add(new ServerEvent(server, channel, copy));
    }

    @Override
    public void run() {
        while (true) {
            try {
                ServerEvent serverEvent = queue.take();
                serverEvent.getServer().send(serverEvent.getChannel(), serverEvent.getData());
            } catch (InterruptedException e) {

            }

        }
    }

}