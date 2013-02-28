package com.tsystems.client;

import com.tsystems.server.Protocol.Message.RequestMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.rmi.UnknownHostException;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 2/22/13
 * Time: 2:59 PM
 * To change this template use File | Settings | File Templates.
 */
public interface MyClient extends Runnable {

    public void sendRequest(RequestMessage requestMessage);

    public void receiveResponse();
}
