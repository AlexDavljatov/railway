package com.tsystems.server.domain.dao;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 3/2/13
 * Time: 2:40 PM
 * To change this template use File | Settings | File Templates.
 */
public interface SingleBaseDAO<T> {

    public T getElement(Object o);

    public boolean addElement(T element);

    public void updateElement(T element);

    public List<T> getAllElements();

}
