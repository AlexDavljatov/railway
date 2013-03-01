package com.tsystems.server.protocol;

import com.tsystems.common.DataTransferObject;
import com.tsystems.common.LoginPassword;
import com.tsystems.common.User;
import com.tsystems.server.domain.entity.Passenger;
import com.tsystems.server.protocol.Command.CommandImpl;
import com.tsystems.server.protocol.Command.CommandType;

import javax.persistence.EntityManager;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 2/27/13
 * Time: 12:09 AM
 * To change this template use File | Settings | File Templates.
 */
public class MyProtocol {
    EntityManager em;

    public MyProtocol(EntityManager em) {
        this.em = em;
    }

    public DataTransferObject processInput(DataTransferObject input) {
        CommandType command = input.getCmd();
        System.err.println("my protocol started, CommandType: " + command);
        if (command == CommandType.REGISTER) {
            User user = (User) input.getData();
            if (new CommandImpl(em).registerUser(new Passenger(user.getName(), user.getSurName(), user.getEmail(), user.getPassword(), user.getBirthdayDate(), false)))
                return new DataTransferObject(CommandType.OK);
            return new DataTransferObject(CommandType.FAIL);
        }
        if (command == CommandType.LOGIN) {
            //TODO: double login exception + logged in state
            LoginPassword loginPassword = (LoginPassword) input.getData();
            if (new CommandImpl(em).login(loginPassword)) return new DataTransferObject(CommandType.OK);
            return new DataTransferObject(CommandType.FAIL);
        }
        if (command == CommandType.ADMIN_USERS_WATCH_EDIT) {
            LoginPassword loginPassword = (LoginPassword) input.getData();
            List<Passenger> passengers = new CommandImpl(em).viewUsers(loginPassword);
            List<User> result = new LinkedList<User>();
            //TODO: administrators privilegies
            for (Passenger passenger : passengers) {
                result.add(new User(passenger.getName(), passenger.getSurname(), passenger.getEmail(), passenger.getPassword(), passenger.getBirthdayDate()));
            }
            return new DataTransferObject(CommandType.OK, result);
        }
        return null;
    }
}
