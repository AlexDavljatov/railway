package com.tsystems.server.protocol;

import com.tsystems.common.*;
import com.tsystems.common.model.*;
import com.tsystems.common.model.Shedule;
import com.tsystems.common.model.Station;
import com.tsystems.common.model.Ticket;
import com.tsystems.common.model.Train;
import com.tsystems.server.domain.entity.*;
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
//   //            for (Passenger passenger : passengers) {
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
        if (command == CommandType.ADMIN_USERS_WATCH_EDIT_DB) {
            Object[] inputData = (Object[]) input.getData();
            LoginPassword loginPassword = (LoginPassword) inputData[0];
            User user = (User) inputData[1];
            log.debug("ADMIN_USERS_WATCH_EDIT_DB" + user + " " + loginPassword.getLogin());
//            if (new CommandImpl(em).isAdmin(loginPassword))
            if (new CommandImpl(em).registerUser(new Passenger(user.getName(), user.getSurName(), user.getEmail(), user.getPassword(), user.getBirthdayDate(), false)))
                return new DataTransferObject(CommandType.OK);
            return new DataTransferObject(CommandType.FAIL);
        }
        if (command == CommandType.ADMIN_ADD_TRAIN) {
            LoginPassword loginPassword = (LoginPassword) input.getData();
            List<com.tsystems.server.domain.entity.Train> trains = new CommandImpl(em).viewAddTrains(loginPassword);
//            List<Train> result = new LinkedList<Train>();
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
        if (command == CommandType.ADMIN_ADD_TRAIN_DB) {
            Object[] inputData = (Object[]) input.getData();
            LoginPassword loginPassword = (LoginPassword) inputData[0];
            Train train = (Train) inputData[1];
            log.debug("ADMIN_ADD_STATION_DB" + train + " " + loginPassword.getLogin());
//            if (new CommandImpl(em).isAdmin(loginPassword))
            if (new CommandImpl(em).addTrainDB(new com.tsystems.server.domain.entity.Train(train.getNumber(), train.getSitsNumber(), null, null)))
                return new DataTransferObject(CommandType.OK);
            return new DataTransferObject(CommandType.FAIL);
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
        if (command == CommandType.ADMIN_ADD_STATION_DB) {
            Object[] inputData = (Object[]) input.getData();
            LoginPassword loginPassword = (LoginPassword) inputData[0];
            Station station = (Station) inputData[1];
            log.debug("ADMIN_ADD_STATION_DB" + (em == null));
            log.debug("ADMIN_ADD_STATION_DB" + station + " " + loginPassword.getLogin());
//            if (new CommandImpl(em).isAdmin(loginPassword)) {
            if (new CommandImpl(em).addStationDB(new com.tsystems.server.domain.entity.Station(station.getName(), null))) {
                return new DataTransferObject(CommandType.OK);
            }
//            }
            return new DataTransferObject(CommandType.FAIL);
        }
        if (command == CommandType.IS_ADMIN) {
            LoginPassword loginPassword = (LoginPassword) input.getData();
            boolean res = new CommandImpl(em).isAdmin(loginPassword);
            log.debug("" + res);
            if (res) return new DataTransferObject(CommandType.OK);
            return new DataTransferObject(CommandType.FAIL);
        }
        if (command == CommandType.GET_SHEDULE_BY_STATION) {
            String station = (String) input.getData();
            List<CommonModel> result = new LinkedList<CommonModel>();
            List<com.tsystems.server.domain.entity.Shedule> sheduleByStation = new CommandImpl(em).getSheduleByStation(station);
            log.debug("GET_SHEDULE_BY_STATION" + sheduleByStation);
            for (com.tsystems.server.domain.entity.Shedule shedule : sheduleByStation) {
//                result.add(new Shedule("" + new CommandImpl(em).getTrainById(shedule.getTrain_id()).getNumber(), shedule.getStation_id(), shedule.getTime()));
                result.add(new Shedule(shedule.getTrain_id(), shedule.getStation_id(), shedule.getTime()));
            }
            return new DataTransferObject(CommandType.OK, result);
        }
        if (command == CommandType.ADMIN_GET_PASSENGERS_BY_TRAIN_NUMBER) {
            String station = (String) input.getData();
            List<CommonModel> result = new LinkedList<CommonModel>();
            List<com.tsystems.server.domain.entity.Passenger> passengersByTrainNumber = new CommandImpl(em).getPassengersByTrainNumber(station);
            log.debug("ADMIN_GET_PASSENGERS_BY_TRAIN_NUMBER" + passengersByTrainNumber);
            for (com.tsystems.server.domain.entity.Passenger passenger : passengersByTrainNumber) {
                //                result.add(new Shedule("" + new CommandImpl(em).getTrainById(shedule.getTrain_id()).getNumber(), shedule.getStation_id(), shedule.getTime()));
                result.add(new User(passenger.getName(), passenger.getSurname(), passenger.getEmail(), passenger.getPassword(), passenger.getBirthdayDate(), passenger.isAdministrator()));
            }
            return new DataTransferObject(CommandType.OK, result);
        }
        if (command == CommandType.GET_SHEDULE_BY_STATION_TEST) {
            String station = (String) input.getData();
            List<CommonModel> result = new LinkedList<CommonModel>();
//            List<com.tsystems.server.domain.entity.Shedule> sheduleByStation = new CommandImpl(em).getSheduleByStation(station);
            Object[] o = new CommandImpl(em).getSheduleByStationTest(station);
            List<com.tsystems.server.domain.entity.Shedule> sheduleByStation = (List<com.tsystems.server.domain.entity.Shedule>) o[0];
            List<com.tsystems.server.domain.entity.Train> trainByStation = (List<com.tsystems.server.domain.entity.Train>) o[1];
            for (com.tsystems.server.domain.entity.Shedule shedule : sheduleByStation) {
                //                result.add(new Shedule("" + new CommandImpl(em).getTrainById(shedule.getTrain_id()).getNumber(), shedule.getStation_id(), shedule.getTime()));
//                result.add(new Shedule(shedule.getTrain_id(), shedule.getStation_id(), shedule.getTime()));
                for (com.tsystems.server.domain.entity.Train train : trainByStation)
                    if (train.getId().equals(shedule.getTrain_id()))
                        result.add(new Shedule(String.valueOf(train.getNumber()), shedule.getStation_id(), shedule.getTime()));
            }
            return new DataTransferObject(CommandType.OK, result);
        }
        if (command == CommandType.VIEW_TICKETS) {
            LoginPassword loginPassword = (LoginPassword) input.getData();
            List<CommonModel> result = new LinkedList<CommonModel>();
            List<com.tsystems.server.domain.entity.Ticket> tickets = new CommandImpl(em).getTickets(loginPassword);

            for (com.tsystems.server.domain.entity.Ticket ticket : tickets) {
//                result.add(new Shedule("" + new CommandImpl(em).getTrainById(shedule.getTrain_id()).getNumber(), shedule.getStation_id(), shedule.getTime()));
                result.add(new Ticket(ticket.getNumber(),
                        new User((ticket.getPassenger()).getName(), (ticket.getPassenger()).getSurname(),
                                (ticket.getPassenger()).getEmail(), (ticket.getPassenger()).getPassword(),
                                (ticket.getPassenger()).getBirthdayDate(), (ticket.getPassenger()).isAdministrator()),
                        new Train((ticket.getTrain()).getNumber(), (ticket.getTrain()).getSits_number())));
                log.debug("" + new Train((ticket.getTrain()).getNumber(), (ticket.getTrain()).getSits_number()).getNumber());
            }
            return new DataTransferObject(CommandType.OK, result);

        }
        return null;
    }
}