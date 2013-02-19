package com.tsystems.Protocol;

import java.nio.channels.SocketChannel;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author Ruslan Sverchkov
 */
public class Worker implements Runnable {
    static int workerIndex;
    private final BlockingQueue<ServerEvent> queue = new ArrayBlockingQueue<ServerEvent>(100);

    Worker() {
        System.out.println("&&& WORKER: default constructor");
    }

    public void process(Server server, SocketChannel channel, byte[] data, int read) {
        System.out.println("&&& WORKER:" + (workerIndex) + " process in");
        byte[] copy = new byte[data.length];
        System.arraycopy(data, 0, copy, 0, read);
        System.out.println("&&& Attempt");
        queue.add(new ServerEvent(server, channel, copy));
        System.out.println("&&& " + queue.size());
        System.out.println("&&& WORKER:" + (workerIndex) + " process out");
    }

    @Override
    public void run() {
        System.out.println("&&& WORKER: run in " + (workerIndex));
        while (true) {
            try {
                System.out.println("&&& Found!!");
                ServerEvent serverEvent = queue.take();
                System.out.println("&&& Found!");
                serverEvent.getServer().send(serverEvent.getChannel(), serverEvent.getData());
                System.out.println("&&& isDone");
            } catch (InterruptedException e) {
                System.out.println("&&& WORKER: run out " + (workerIndex++) + " Exception");
            }
        }
    }

}