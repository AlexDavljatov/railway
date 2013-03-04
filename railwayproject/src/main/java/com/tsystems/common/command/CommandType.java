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
    VIEW_TICKETS,
    GET_SHEDULE_BY_STATION,
    GET_SHEDULE_BY_STATION_TEST,
    BUY_TICKET,
    FIND_TRAIN,
    ADMIN_WATCH_USERS_TICKETS,
    ADMIN_ADD_TRAIN,
    ADMIN_ADD_TRAIN_DB,
    ADMIN_ADD_STATION,
    ADMIN_ADD_STATION_DB,
    ADMIN_TRAINS,
    ADMIN_USERS_WATCH_EDIT,
    ADMIN_USERS_WATCH_EDIT_DB,
    ADMIN_GET_PASSENGERS_BY_TRAIN_NUMBER,
    UPDATE_DATA,
    GET_ANOTHER_SHEDULE_BY_STATION, IS_ADMIN
}
