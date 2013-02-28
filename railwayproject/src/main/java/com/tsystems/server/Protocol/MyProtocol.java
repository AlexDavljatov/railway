package com.tsystems.server.Protocol;

import com.tsystems.common.DataTransferObject;
import com.tsystems.common.User;
import com.tsystems.server.Protocol.Command.CommandImpl;
import com.tsystems.server.Protocol.Command.CommandType;
import com.tsystems.server.domain.entity.Passenger;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 2/27/13
 * Time: 12:09 AM
 * To change this template use File | Settings | File Templates.
 */
public class MyProtocol {
    public DataTransferObject processInput(DataTransferObject input) {
        System.err.println("my protocol started");
        CommandType command = input.getCmd();
        if (command == CommandType.REGISTER) {
            User user = (User) input.getData();
            new CommandImpl().registerUser(new Passenger(user.getName(), user.getSurName(), user.getEmail(), user.getPassword(), false));
            return new DataTransferObject(CommandType.OK);
        }
        return null;
    }
}
