package com.tsystems.common;

import com.tsystems.server.domain.entity.Passenger;
import com.tsystems.server.protocol.Command.CommandType;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 2/27/13
 * Time: 12:09 AM
 * To change this template use File | Settings | File Templates.
 */
public class DataTransferObject implements Serializable {

    public DataTransferObject() {
    }

    private CommandType cmd;

    private Object data;


    public CommandType getCmd() {
        return cmd;
    }

    public DataTransferObject(CommandType cmd) {
        this.cmd = cmd;
    }


    public DataTransferObject(CommandType cmd, LoginPassword loginPassword) {
        this.cmd = cmd;
        data = loginPassword;
    }


    public DataTransferObject(CommandType cmd, User user) {
        this.cmd = cmd;
        data = user;
    }


    public DataTransferObject(CommandType cmd, List<User> allElements) {
        this.cmd = cmd;
        data = allElements;
    }

    public Object getData() {
        return data;
    }

}
