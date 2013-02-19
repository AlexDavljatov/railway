package com.tsystems.Protocol;

import java.nio.channels.SocketChannel;

/**
 * @author Ruslan Sverchkov
 */
public class ChangeRequest {

    private final SocketChannel channel;
    private final int operation;

    public ChangeRequest(SocketChannel channel, int operation) {
        this.channel = channel;
        this.operation = operation;
    }

    public SocketChannel getChannel() {
        return channel;
    }

    public int getOperation() {
        return operation;
    }

}