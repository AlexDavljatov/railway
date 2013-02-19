package com.tsystems.Protocol;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 2/22/13
 * Time: 2:55 PM
 * To change this template use File | Settings | File Templates.
 */
public interface MyServer extends Runnable {
    public void receive();
    //public void send();
    public void reply();
}
