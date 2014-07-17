package org.invoices.service;

import java.util.List;

public interface GenericDao<E, K> {
 
    void add(E entity);
     
    void update(E entity);
     
    void remove(E entity);
     
    E find(K key);
     
    List<E> list(int pageIndex, int pageSize);
    
    public long count();
     
}
