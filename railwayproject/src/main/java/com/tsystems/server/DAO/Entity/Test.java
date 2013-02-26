package com.tsystems.server.DAO.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 2/27/13
 * Time: 3:32 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Test {
    public Test() {
    }

    @Id
    private String id;

    @PrePersist
    public void prepareId() {
        if (id == null) {
            id = UUID.randomUUID().toString();
        }
    }
}
