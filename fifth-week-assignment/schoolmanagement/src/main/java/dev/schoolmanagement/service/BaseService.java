package dev.schoolmanagement.service;

import dev.schoolmanagement.exceptions.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

/**
 * Base service interface for basic crud operations.
 *
 * @param <T> generic type for implementing sub-classes.
 */
public interface BaseService<T> {
    /**
     * Basic persist operation which takes a
     * generic type of object and returns the peristed
     * data if it is successfull.
     *
     * @param t object to be persisted.
     * @return the persisted object.
     */
    T save(T t);

    /**
     * Retrieves all persisted data of the type T without
     * pagination or sorting. Might return empty list if the associated
     * table is empty.
     *
     * @return {@link List} of the retrieved objects.
     */
    List<T> findAll();

    /**
     * Retrieves the {@link Optional} type of entity with the associated ID
     * and converts it to the corresponding data transfer object by using its mapper class.
     * Throws {@link EntityNotFoundException} if the entity is non-existent.
     *
     * @param id of the desired entity.
     * @return DTO of the retrieved entity.
     */
    T findById(long id);

    /**
     * Deletes the persistent entity from database by it is ID,
     * throws {@link EntityNotFoundException} if the entity is non-existent.
     *
     * @param id of the desired entity.
     */
    default void deleteById(long id) {
    }

    ;

    /**
     * Update operation on a persistent entity. First checks if there is a
     * entity associated, if not throws {@link EntityNotFoundException} .
     *
     * @param t object with updated fields.
     * @return updated object.
     */
    default T update(T t) {
        return null;
    }

    ;
}
