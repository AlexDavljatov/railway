package com.tsystems.server.protocol;

import com.tsystems.common.*;
import com.tsystems.common.model.*;
import com.tsystems.server.domain.entity.Passenger;
import com.tsystems.server.protocol.Command.CommandImpl;
import com.tsystems.common.command.CommandType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private static final Logger log = LoggerFactory.getLogger(CommandImpl.class);

    EntityManager em;

    public MyProtocol(EntityManager em) {
        this.em = em;
    }

    public DataTransferObject processInput(DataTransferObject input) {
        CommandType command = input.getCmd();
        log.debug("my protocol started, CommandType: " + command);
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
//            List<User> result = new LinkedList<User>();
//            //TODO: administrators privilegies
//            for (Passenger passenger : passengers) {
//                result.add(new User(passenger.getName(), passenger.getSurname(), passenger.getEmail(), passenger.getPassword(), passenger.getBirthdayDate()));
//            }
//            return new DataTransferObject(CommandType.OK, result);
            List<CommonModel> result = new LinkedList<CommonModel>();
            //TODO: administrators privilegies
            for (Passenger passenger : passengers) {
                result.add(new User(passenger.getName(), passenger.getSurname(), passenger.getEmail(),
                        passenger.getPassword(), passenger.getBirthdayDate(), passenger.isAdministrator()));
            }
            return new DataTransferObject(CommandType.OK, result);

        }
        if (command == CommandType.ADMIN_ADD_TRAIN) {
            LoginPassword loginPassword = (LoginPassword) input.getData();
            List<com.tsystems.server.domain.entity.Train> trains = new CommandImpl(em).viewAddTrains(loginPassword);
//            List<Train> result = new LinkedList<Train>();
//            //TODO: administrators privilegies
//            for (com.tsystems.server.domain.entity.Train train: trains) {
//                result.add(new Train(train.getNumber(), train.getSits_number()));
//            }
//            return new DataTransferObject(CommandType.OK, result);
            List<CommonModel> result = new LinkedList<CommonModel>();
            //TODO: administrators privilegies
            for (com.tsystems.server.domain.entity.Train train : trains) {
                result.add(new Train(train.getNumber(), train.getSits_number()));
            }
            return new DataTransferObject(CommandType.OK, result);

        }
        if (command == CommandType.ADMIN_ADD_STATION) {
            LoginPassword loginPassword = (LoginPassword) input.getData();
            List<com.tsystems.server.domain.entity.Station> stations = new CommandImpl(em).viewAddStations(loginPassword);
            List<CommonModel> result = new LinkedList<CommonModel>();
            log.debug(stations.toString());
            //TODO: administrators privilegies
            for (com.tsystems.server.domain.entity.Station station : stations) {
                result.add(new Station(station.getName()));
            }
            return new DataTransferObject(CommandType.OK, result);
        }
        if (command == CommandType.IS_ADMIN) {
            LoginPassword loginPassword = (LoginPassword) input.getData();
            boolean res = new CommandImpl(em).isAdmin(loginPassword);
            log.debug("" + res);
            if (res) return new DataTransferObject(CommandType.OK);
            return new DataTransferObject(CommandType.FAIL);
        }
        if (command == CommandType.GET_SHEDULE_BY_STATION) {
            Station station = (Station) input.getData();
            List<CommonModel> result = new LinkedList<CommonModel>();
            List<com.tsystems.server.domain.entity.Shedule> sheduleByStation = new CommandImpl(em).getSheduleByStation(station.getName());
            for (com.tsystems.server.domain.entity.Shedule shedule : sheduleByStation) {
                result.add(new Shedule(shedule.getTrain_id(), shedule.getStation_id(), shedule.getTime()));
            }
            return new DataTransferObject(CommandType.OK, result);
        }
        return null;
    }
}
