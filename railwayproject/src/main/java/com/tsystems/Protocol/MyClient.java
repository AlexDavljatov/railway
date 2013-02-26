package com.tsystems.Protocol;

import com.tsystems.Protocol.Message.RequestMessage;

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
