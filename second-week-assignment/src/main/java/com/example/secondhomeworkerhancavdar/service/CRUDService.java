package com.example.secondhomeworkerhancavdar.service;

import java.util.List;

public interface CRUDService<T> {
    List<T> findAll();

    T findById(long id);

    T save(T t);

    void delete(long id);

    void update(T t);
}
