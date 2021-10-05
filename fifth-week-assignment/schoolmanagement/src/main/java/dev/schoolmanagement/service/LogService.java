package dev.schoolmanagement.service;


import dev.schoolmanagement.DTO.ExceptionLogDTO;

import java.util.List;

/**
 * Base service for CRUD operations on Log models.
 */
public interface LogService <T> { // To be replaced with base log entity
    T save(T t);
    List<T> findAll();
    T findById(long id);
}
