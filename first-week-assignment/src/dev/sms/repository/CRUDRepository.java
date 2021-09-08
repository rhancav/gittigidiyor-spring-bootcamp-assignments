package dev.sms.repository;

import java.util.List;

public interface CRUDRepository <T>{
    void save(T t);
    void delete(T t);
    void delete(long id);
    void update(T t, long id);
    List<T> findAll();
    T findById(long id);
}
