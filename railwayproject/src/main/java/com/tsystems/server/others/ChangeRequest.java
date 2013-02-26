package com.tsystems.server.others;

import java.nio.channels.SocketChannel;

/**
 * @author Ruslan Sverchkov
 */
public class ChangeRequest {

    private final SocketChannel channel;
    private final int operation;

    public ChangeRequest(SocketChannel channel, int operation) {
        System.out.println("*** CHANGEREQUEST in");
        this.channel = channel;
        this.operation = operation;
    }

    public SocketChannel getChannel() {
        System.out.println("*** CHANGEREQUEST getChannel in");
        return channel;
    }

    public int getOperation() {
        System.out.println("*** CHANGEREQUEST getOperation in");
        return operation;
    }

}