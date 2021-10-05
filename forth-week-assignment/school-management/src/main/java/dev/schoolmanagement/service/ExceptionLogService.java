package dev.schoolmanagement.service;


import dev.schoolmanagement.DTO.ExceptionLogDTO;

import java.time.Instant;
import java.util.List;

/**
 * Base ExceptionLogService interface for ExceptionLog service layer.
 */
public interface ExceptionLogService extends LogService {
    /**
     * Get all the exception logs by their creation date.
     *
     * @param creationDate {@link Instant} type of creation date.
     * @return the list of found logs.
     */
    List<ExceptionLogDTO> findAllByCreationDate(Instant creationDate);

    /**
     * Get all the exception logs by their exception types.
     *
     * @param type String value of the thrown exceptions type.
     * @return the list of found logs.
     */
    List<ExceptionLogDTO> findAllByType(String type);
}