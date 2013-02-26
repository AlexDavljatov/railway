package com.tsystems.server.others;

import java.nio.channels.SocketChannel;

/**
 * @author Ruslan Sverchkov
 */
public class ServerEvent {

    private final Server server;
    private final SocketChannel channel;
    private final byte[] data;

    public ServerEvent(Server server, SocketChannel channel, byte[] data) {
        System.out.println("### SERVEREVENT: in");
        this.server = server;
        this.channel = channel;
        this.data = data;
    }

    public Server getServer() {
        System.out.println("### SERVEREVENT: getServer in");
        return server;
    }

    public byte[] getData() {
        System.out.println("### SERVEREVENT: getData in");
        return data;
    }

    public SocketChannel getChannel() {
        System.out.println("### SERVEREVENT: getChannel in");
        return channel;
    }

}