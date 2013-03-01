package com.tsystems.server.domain.dao;

import com.tsystems.server.domain.entity.Passenger;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 2/28/13
 * Time: 2:08 AM
 * To change this template use File | Settings | File Templates.
 */
public interface BaseDAO<T> {

    public List<T> getAllElements();

    public T getElement(Object o);

    public void addElement(T element);

    public void removeElement(T element);

}
