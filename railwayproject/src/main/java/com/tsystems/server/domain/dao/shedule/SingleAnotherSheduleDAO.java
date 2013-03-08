package com.tsystems.server.domain.dao.shedule;

import com.tsystems.server.domain.dao.SingleBaseDAO;
import com.tsystems.server.domain.entity.AnotherShedule;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 3/4/13
 * Time: 3:47 PM
 * To change this template use File | Settings | File Templates.
 */
public interface SingleAnotherSheduleDAO extends SingleBaseDAO<AnotherShedule> {
    List<AnotherShedule> getSheduleByStationName(String station);
}
