package com.tsystems.common;

import com.tsystems.server.Protocol.Command.CommandType;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 2/27/13
 * Time: 12:09 AM
 * To change this template use File | Settings | File Templates.
 */
public class DataTransferObject implements Serializable {
    private CommandType cmd;
    private Object data;

    public CommandType getCmd() {
        return cmd;
    }

    public DataTransferObject(CommandType cmd, User user) {
        this.cmd = cmd;
        data = user;
    }

    public DataTransferObject(CommandType cmd) {
        this.cmd = cmd;
    }

    public Object getData() {
        return data;
    }

}
