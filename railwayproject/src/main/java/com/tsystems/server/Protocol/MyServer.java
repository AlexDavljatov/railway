package com.tsystems.server.Protocol;

import com.tsystems.server.Protocol.Message.RequestMessage;
import com.tsystems.server.Protocol.Message.ResponseMessage;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 2/22/13
 * Time: 2:55 PM
 * To change this template use File | Settings | File Templates.
 */
public interface MyServer extends Runnable {
    public void receiveRequest(RequestMessage requestMessage);

    //public void send();
    public void replyResponse(ResponseMessage responseMessage);
}
