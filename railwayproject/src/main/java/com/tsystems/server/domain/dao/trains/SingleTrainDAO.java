package com.tsystems.server.domain.dao.trains;

import com.tsystems.server.domain.dao.SingleBaseDAO;
import com.tsystems.server.domain.entity.Train;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 3/2/13
 * Time: 2:42 PM
 * To change this template use File | Settings | File Templates.
 */
public interface SingleTrainDAO extends SingleBaseDAO<Train>{

    Train getElementById(String id);
}
