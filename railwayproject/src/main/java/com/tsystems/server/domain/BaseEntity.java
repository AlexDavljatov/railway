package com.tsystems.server.domain;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Version;
import java.io.Serializable;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 2/28/13
 * Time: 1:13 AM
 * To change this template use File | Settings | File Templates.
 */
@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    protected String id;

    public void setId(String id) {
        this.id = id;
    }

    /*   @PrePersist
       public void prepareId() {
           if (id == null) {
               id = UUID.randomUUID().toString();
           }
       }
   */
    @Version
    protected int version;

    public void setVersion(int version) {
        this.version = version;
    }

}