package com.example.schoolmanagement.service;

import java.util.List;

public interface BaseService<T> {
    List<T> findAll();

    T findById(long id);

    T save(T t);

    T findByName(String name);

    void deleteByName(String name);

    void delete(long id);

    void delete(T t);

    void update(T t);
}
