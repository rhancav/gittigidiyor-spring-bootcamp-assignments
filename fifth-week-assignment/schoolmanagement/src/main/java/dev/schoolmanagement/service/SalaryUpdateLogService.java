package dev.schoolmanagement.service;

import dev.schoolmanagement.entity.SalaryUpdateLog;

import java.time.LocalDate;

/**
 * Salary log service.
 */
public interface SalaryUpdateLogService extends LogService<SalaryUpdateLog> {
    /**
     * Finds salary update log by the given instructor id and
     * created date in the format of LocalDate. Since the RequestTime is
     * persisted as Instant type, it should be converted.
     *
     * @param instructorId Id of the instructor
     * @param createdDate  request time
     * @return the log object
     */
    SalaryUpdateLog findByInstructorAndDate(Long instructorId, LocalDate createdDate);
}
