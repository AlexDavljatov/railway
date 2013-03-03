package com.tsystems.common.command;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 2/27/13
 * Time: 2:14 PM
 * To change this template use File | Settings | File Templates.
 */
public enum CommandType {
    OK,
    FAIL,
    LOGIN,
    REGISTER,
    GET_SHEDULE_BY_STATION,
    BUY_TICKET,
    FIND_TRAIN,
    ADMIN_WATCH_USERS_TICKETS,
    ADMIN_ADD_TRAIN,
    ADMIN_ADD_STATION,
    ADMIN_TRAINS,
    ADMIN_USERS_WATCH_EDIT,
    UPDATE_DATA,
    IS_ADMIN
}
