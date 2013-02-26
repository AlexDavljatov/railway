package com.tsystems.common;

import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 2/27/13
 * Time: 12:09 AM
 * To change this template use File | Settings | File Templates.
 */
public class DataTransferObject {
    public static enum CommandType {REGISTER, LOGIN, LOGOUT, SEND, GET_INBOX, GET_OUTBOX, OK, ERROR}
    //public static enum State {LOGGED_IN, LOGGED_OUT}

    //private State state = State.LOGGED_OUT;

    public DataTransferObject processInput(DataTransferObject input) throws SQLException {
        //        CommandType cmd = input.getCmd();
        //        return new DataTransferObject(CommandType.OK);
    /*        if (state == State.LOGGED_OUT) {
                if (cmd == CommandType.REGISTER) {
                    User user = (User)input.getData();
                    if (DataAccessObject.tryRegister(user)) {
                        return new DataTransferObject(CommandType.OK);
                    } else {
                        return new DataTransferObject(CommandType.ERROR);
                    }
                } else if (cmd == CommandType.LOGIN) {
                    LoginPassword loginPassword = (LoginPassword)input.getData();
                    if (DataAccessObject.tryLogin(loginPassword)) {
                        state = State.LOGGED_IN;
                        return new DataTransferObject(CommandType.OK);
                    } else {
                        return new DataTransferObject(CommandType.ERROR);
                    }
                } else {
                    return new DataTransferObject(CommandType.ERROR);
                }
            } else if (state == State.LOGGED_IN) {
                if (cmd == CommandType.GET_INBOX) {
                    String userLogin = (String)input.getData();
                    List<Email> emails = DataAccessObject.getInbox(userLogin);
                    return new DataTransferObject(CommandType.OK, emails);
                } else if (cmd == CommandType.GET_OUTBOX) {
                    String userLogin = (String)input.getData();
                    List<Email> emails = DataAccessObject.getOutbox(userLogin);
                    return new DataTransferObject(CommandType.OK, emails);
                } else if (cmd == CommandType.SEND) {
                    Email email = (Email)input.getData();
                    if (DataAccessObject.addEmail(email)) {
                        return new DataTransferObject(CommandType.OK);
                    } else {
                        return new DataTransferObject(CommandType.ERROR);
                    }
                } else if (cmd == CommandType.LOGOUT) {
                    state = State.LOGGED_OUT;
                    return new DataTransferObject(CommandType.OK);
                } else {
                    return new DataTransferObject(CommandType.ERROR);
                }
            }
            */
        return null;
    }

}
