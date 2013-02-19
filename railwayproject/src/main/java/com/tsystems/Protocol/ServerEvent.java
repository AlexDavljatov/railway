package com.tsystems.Protocol;

import java.nio.channels.SocketChannel;

/**
 * @author Ruslan Sverchkov
 */
public class ServerEvent {

    private final Server server;
    private final SocketChannel channel;
    private final byte[] data;

    public ServerEvent(Server server, SocketChannel channel, byte[] data) {
        this.server = server;
        this.channel = channel;
        this.data = data;
    }

    public Server getServer() {
        return server;
    }

    public byte[] getData() {
        return data;
    }

    public SocketChannel getChannel() {
        return channel;
    }

}