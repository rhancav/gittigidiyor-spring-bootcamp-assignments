package com.example.secondhomeworkerhancavdar.repositories;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CRUDRepository<T> {
    List<T> findAll();

    T findById(long id);

    T save(T t);

    void delete(long id);

    void update(T t);
}
