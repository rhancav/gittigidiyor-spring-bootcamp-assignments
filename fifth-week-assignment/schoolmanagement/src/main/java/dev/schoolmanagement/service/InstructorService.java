package dev.schoolmanagement.service;

import dev.schoolmanagement.DTO.InstructorDTO;
import dev.schoolmanagement.DTO.request.InstructorSalaryUpdate;
import dev.schoolmanagement.entity.Instructor;

/**
 * Base interface for basic CRUD operations on Instructor types.
 */
public interface InstructorService extends BaseService<InstructorDTO> {

    Instructor updateSalary(Long id, InstructorSalaryUpdate instructorSalaryUpdate);
}