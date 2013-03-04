package com.tsystems.common;

import com.tsystems.common.model.CommonModel;
import com.tsystems.common.model.LoginPassword;
import com.tsystems.common.model.User;
import com.tsystems.common.command.CommandType;

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

//    public DataTransferObject(CommandType cmd, List<Train> allElements) {
//        this.cmd = cmd;
//        data = allElements;
//    }


    public DataTransferObject(CommandType cmd, List<CommonModel> allElements) {
        this.cmd = cmd;
        data = allElements;
    }

    public DataTransferObject(CommandType cmd, LoginPassword lp, CommonModel element) {
        this.cmd = cmd;
        data = new Object[]{lp, element};
    }

    public DataTransferObject(CommandType cmd, String s) {
        this.cmd = cmd;
        data = s;
    }


    public Object getData() {
        return data;
    }

}
